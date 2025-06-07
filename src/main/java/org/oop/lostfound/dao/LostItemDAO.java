package org.oop.lostfound.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import org.oop.lostfound.enums.Category;


public class LostItemDAO {
    private Connection connection;

    public LostItemDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean insertLostItem(String itemName, String description, String location, LocalDate dateLost, Category category, String image_url, String contact) {
        String sql = "INSERT INTO lost_item (item_name, description_item, location_item, date_lost, category, image_url, contact) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, itemName);
            stmt.setString(2, description);
            stmt.setString(3, location);
            stmt.setDate(4, java.sql.Date.valueOf(dateLost));
            stmt.setString(5, category.name());
            stmt.setString(6, image_url);
            stmt.setString(7, contact);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getJumlahLostItems() {
    String sql = "SELECT COUNT(*) FROM lost_item";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        var rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
    }
}
