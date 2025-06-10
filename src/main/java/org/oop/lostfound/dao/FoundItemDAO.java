package org.oop.lostfound.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.oop.lostfound.enums.Category;
import org.oop.lostfound.model.FoundItem;


public class FoundItemDAO {
    private Connection connection;

    public FoundItemDAO() {
        this.connection = Connector.getConnection();
    }

    public boolean insertFoundItem(String itemName, String description, String location, String image_url, LocalDate dateFound, Category category, String contact, int idAccount) {
        String sql = "INSERT INTO found_item (item_name, description_item, location_item, image_url, date_found, category, contact, id_account) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Connector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, itemName);
            stmt.setString(2, description);
            stmt.setString(3, location);
            stmt.setString(4, image_url);
            stmt.setDate(5, java.sql.Date.valueOf(dateFound));
            stmt.setString(6, category.name());
            stmt.setString(7, contact);
            stmt.setInt(8, idAccount); // Assuming id_account is 1 for the logged-in user, adjust as necessary
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getJumlahFoundItems() {
    String sql = "SELECT COUNT(*) FROM found_item";
    try (Connection conn = Connector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
        var rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
    }

    public List<FoundItem> getAllFoundItems() throws Exception {
        List<FoundItem> foundItems = new ArrayList<>();
        String sql = "SELECT * FROM found_item";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                FoundItem item = new FoundItem(
                    rs.getInt("id_item"),
                    rs.getString("item_name"),
                    rs.getString("description_item"),
                    rs.getString("location_item"),
                    org.oop.lostfound.enums.Category.valueOf(rs.getString("category")),
                    rs.getString("contact"),
                    rs.getString("image_url"),
                    rs.getDate("date_found") != null ? rs.getDate("date_found").toLocalDate() : null
                );
                foundItems.add(item);
            }
        }
        return foundItems;
    }

    public void deleteFoundItemById(int id) {

        String sql = "DELETE FROM found_item WHERE id_item = ?";
        try (Connection conn =  Connector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
