package org.oop.lostfound.dao;

import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import org.oop.lostfound.enums.Category;
import org.oop.lostfound.model.LostItem;


public class LostItemDAO {
    private Connection connection;

    public LostItemDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean insertLostItem(String itemName, String description, String location, LocalDate dateLost, Category category, String image_url, String contact, int idAccount) {
        String sql = "INSERT INTO lost_item (item_name, description_item, location_item, date_lost, category, image_url, contact, id_account) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, itemName);
            stmt.setString(2, description);
            stmt.setString(3, location);
            stmt.setDate(4, java.sql.Date.valueOf(dateLost));
            stmt.setString(5, category.name());
            stmt.setString(6, image_url);
            stmt.setString(7, contact);
            stmt.setInt(8, idAccount); // Assuming id_account is 1 for the logged-in user, adjust as necessary
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            Logger.getLogger(LostItemDAO.class.getName()).log(Level.SEVERE, "Error inserting lost item", e);

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

    public List<LostItem> getAllLostItems() {
        List<LostItem> lostItems = new ArrayList<>();
        String sql = "SELECT * FROM lost_item"; // Adjust table name and columns as needed
        try (PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                LostItem item = new LostItem(0, sql, sql, sql, null, sql, sql, null);
                item.setId(rs.getInt("id_item")); // Adjust column names as needed
                item.setName(rs.getString("item_name")); // Use the correct setter as defined in LostItem
                item.setImageUrl(rs.getString("image_url"));
                // Set other fields if needed
                lostItems.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lostItems;
    }
}
