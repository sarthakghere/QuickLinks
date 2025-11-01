package com.quicklinks.dao;

import com.quicklinks.model.ShortLink;
import com.quicklinks.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShortLinkDAO {

    public boolean addShortLink(ShortLink link) {
        String sql = "INSERT INTO short_links (user_id, original_url, short_code) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, link.getUserId());
            ps.setString(2, link.getOriginalUrl());
            ps.setString(3, link.getShortCode());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ShortLink getByShortCode(String shortCode) {
        String sql = "SELECT * FROM short_links WHERE short_code = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, shortCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return extractLink(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ShortLink> getByUserId(int userId) {
        List<ShortLink> list = new ArrayList<>();
        String sql = "SELECT * FROM short_links WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(extractLink(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void incrementVisitCount(int linkId) {
        String sql = "UPDATE short_links SET total_visits = total_visits + 1 WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, linkId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ShortLink extractLink(ResultSet rs) throws SQLException {
        return new ShortLink(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getString("original_url"),
                rs.getString("short_code"),
                rs.getTimestamp("created_at"),
                rs.getInt("total_visits")
        );
    }
}
