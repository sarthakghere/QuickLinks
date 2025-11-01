package com.quicklinks.dao;

import com.quicklinks.model.EmailShare;
import com.quicklinks.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmailShareDAO {

    public boolean addEmailShare(EmailShare share) {
        String sql = "INSERT INTO email_shares (link_id, sender_id, recipient_email) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, share.getLinkId());
            ps.setInt(2, share.getSenderId());
            ps.setString(3, share.getRecipientEmail());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<EmailShare> getBySenderId(int senderId) {
        List<EmailShare> list = new ArrayList<>();
        String sql = "SELECT * FROM email_shares WHERE sender_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, senderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(extractShare(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private EmailShare extractShare(ResultSet rs) throws SQLException {
        return new EmailShare(
                rs.getInt("id"),
                rs.getInt("link_id"),
                rs.getInt("sender_id"),
                rs.getString("recipient_email"),
                rs.getTimestamp("sent_at")
        );
    }
}
