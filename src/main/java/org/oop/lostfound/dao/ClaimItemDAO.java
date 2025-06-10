package org.oop.lostfound.dao;

import org.oop.lostfound.model.Claim;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

public class ClaimItemDAO {

    private Connection connection;

    public ClaimItemDAO() {
        this.connection = connection;
    }
    public void insertClaimItem(Claim claimItem) {
        String sql = "INSERT INTO claim_item (id, item_name, finder_name, found_date, description, image_url, claimant_name, claimant_phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Connector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, claimItem.getId());
            stmt.setString(2, claimItem.getItemName());
            stmt.setString(3, claimItem.getFinderName());
            stmt.setDate(4, claimItem.getFoundDate() != null ? Date.valueOf(claimItem.getFoundDate()) : null);
            stmt.setString(5, claimItem.getDescription());
            stmt.setString(6, claimItem.getImageUrl());
            stmt.setString(7, claimItem.getClaimantName());
            stmt.setString(8, claimItem.getClaimantPhone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
