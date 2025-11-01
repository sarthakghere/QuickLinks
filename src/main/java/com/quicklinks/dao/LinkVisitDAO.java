package com.quicklinks.dao;
import com.quicklinks.utils.DBConnection;
import java.sql.*;

public class LinkVisitDAO {

    public void recordVisit(int linkId) {
        String sql = "INSERT INTO link_visits (link_id) VALUES (?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, linkId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getVisitCount(int linkId) {
        String sql = "SELECT COUNT(*) AS total FROM link_visits WHERE link_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, linkId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("total");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
