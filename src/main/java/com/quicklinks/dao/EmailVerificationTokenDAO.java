package com.quicklinks.dao;

import com.quicklinks.model.EmailVerificationToken;
import com.quicklinks.utils.DBConnection;

import java.sql.*;

public class EmailVerificationTokenDAO {

    public void createToken(EmailVerificationToken token) {
        String sql = "INSERT INTO email_verification_tokens (user_id, token, expiry) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, token.getUserId());
            ps.setString(2, token.getToken());
            ps.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis() + 24 * 60 * 60 * 1000));

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public EmailVerificationToken findByToken(String token) {
        String sql = "SELECT * FROM email_verification_tokens WHERE token = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, token);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return extractToken(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteToken(int id) {
        String sql = "DELETE FROM email_verification_tokens WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private EmailVerificationToken extractToken(ResultSet rs) {
        try {
            return new EmailVerificationToken(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("token"),
                    rs.getTimestamp("expiry")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
