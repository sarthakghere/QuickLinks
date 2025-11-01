package com.quicklinks.dao;

import com.quicklinks.model.EmailVerificationToken;
import com.quicklinks.utils.DBConnection;

import java.sql.*;

public class EmailVerificationTokenDAO {

    public boolean addToken(EmailVerificationToken token) {
        String sql = "INSERT INTO email_verification_tokens (user_id, token, expiry) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, token.getUserId());
            ps.setString(2, token.getToken());
            ps.setTimestamp(3, token.getExpiry());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public EmailVerificationToken getByToken(String tokenStr) {
        String sql = "SELECT * FROM email_verification_tokens WHERE token = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tokenStr);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return extractToken(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteExpired() {
        String sql = "DELETE FROM email_verification_tokens WHERE expiry < NOW()";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private EmailVerificationToken extractToken(ResultSet rs) throws SQLException {
        return new EmailVerificationToken(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getString("token"),
                rs.getTimestamp("expiry")
        );
    }
}
