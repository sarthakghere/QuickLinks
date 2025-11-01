package com.quicklinks.dao;

import com.quicklinks.model.PasswordResetToken;
import com.quicklinks.utils.DBConnection;

import java.sql.*;

public class PasswordResetTokenDAO {

    public boolean addToken(PasswordResetToken token) {
        String sql = "INSERT INTO password_reset_tokens (user_id, token, expiry) VALUES (?, ?, ?)";
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

    public PasswordResetToken getByToken(String tokenStr) {
        String sql = "SELECT * FROM password_reset_tokens WHERE token = ?";
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
        String sql = "DELETE FROM password_reset_tokens WHERE expiry < NOW()";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private PasswordResetToken extractToken(ResultSet rs) throws SQLException {
        return new PasswordResetToken(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getString("token"),
                rs.getTimestamp("expiry")
        );
    }
}
