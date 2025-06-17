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
        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setString(1, itemName);
            stmt.setString(2, description);
            stmt.setString(3, location);
            stmt.setDate(4, java.sql.Date.valueOf(dateLost));
            stmt.setString(5, category.name());
            stmt.setString(6, image_url);
            stmt.setString(7, contact);
            stmt.setInt(8, idAccount);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            Logger.getLogger(LostItemDAO.class.getName()).log(Level.SEVERE, "Error inserting lost item", e);

            return false;
        }
    }

    public int getJumlahLostItems()
    {
        String sql = "SELECT COUNT(*) FROM lost_item";
        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            var rs = stmt.executeQuery();
            if (rs.next())
            {
                return rs.getInt(1);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public List<LostItem> getAllLostItems()
    {
        List<LostItem> lostItems = new ArrayList<>();
        String sql = "SELECT * FROM lost_item"; 
        try (PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery())
        {
            while (rs.next())
            {
                LostItem item = new LostItem(
                    rs.getInt("id_item"),
                    rs.getString("item_name"),
                    rs.getString("description_item"),
                    rs.getString("location_item"),
                    Category.valueOf(rs.getString("category")),
                    rs.getString("contact"),
                    rs.getString("image_url"),
                    rs.getDate("date_lost").toLocalDate()
                );
                lostItems.add(item);
                
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return lostItems;
    }

    public boolean deleteLostItemById(int id)
    {
        String sql = "DELETE FROM lost_item WHERE id_item = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            Logger.getLogger(LostItemDAO.class.getName()).log(Level.SEVERE, "Error deleting lost item", e);
            return false;
        }
    }
}
