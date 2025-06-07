package org.oop.lostfound.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import org.oop.lostfound.enums.Category;


public class FoundItemDAO {
    private Connection connection;

    public FoundItemDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean insertFoundItem(String itemName, String description, String location, String image_url, LocalDate dateFound, Category category, String contact) {
        String sql = "INSERT INTO found_item (item_name, description_item, location_item, image_url, date_found, category, contact) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, itemName);
            stmt.setString(2, description);
            stmt.setString(3, location);
            stmt.setString(4, image_url);
            stmt.setDate(5, java.sql.Date.valueOf(dateFound));
            stmt.setString(6, category.name());
            stmt.setString(7, contact);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getJumlahFoundItems() {
    String sql = "SELECT COUNT(*) FROM found_item";
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
