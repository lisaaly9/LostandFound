package org.oop.lostfound.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import org.oop.lostfound.enums.Category;
import org.oop.lostfound.enums.ItemType;

public class LostItemDAO {
    private Connection connection;

    public LostItemDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean insertLostItem(String itemName, String description, String location, LocalDate dateLost, Category category, ItemType type) {
        String sql = "INSERT INTO lost_item (item_name, description_item, location_item, date_lost, category, item_type) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, itemName);
            stmt.setString(2, description);
            stmt.setString(3, location);
            stmt.setDate(4, java.sql.Date.valueOf(dateLost));
            stmt.setString(5, category.name());
            stmt.setString(6, type.name());
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
