package org.oop.lostfound.dao;

import org.oop.lostfound.model.Claim;
import org.oop.lostfound.enums.ClaimStatus;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClaimDAO {
    public static List<Claim> getAllClaims() {
        List<Claim> claims = new ArrayList<>();

        String query = """
            SELECT 
                c.id_claim,
                c.claim_date,
                c.status,
                a.username AS claimant,
                fi.item_name AS item_name
            FROM claims c
            JOIN account a ON c.id_account = a.id_account
            JOIN found_item fi ON c.id_found_item = fi.id_item
            ORDER BY c.claim_date DESC
            """;

        try (Connection conn = Connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Claim claim = new Claim(
                    rs.getInt("id_claim"),
                    rs.getTimestamp("claim_date").toLocalDateTime().toLocalDate(),
                    ClaimStatus.valueOf(rs.getString("status")),
                    rs.getString("claimant"),
                    rs.getString("item_name")
                );
                claims.add(claim);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return claims;
    }

    public static void createClaim(Integer accountId, Integer foundItemId) throws SQLException {
        String query = "INSERT INTO claims (id_account, id_found_item, status) VALUES (?, ?, ?)";
        
        try (Connection conn = Connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, accountId);
            stmt.setInt(2, foundItemId);
            stmt.setString(3, ClaimStatus.PENDING.name());
            stmt.executeUpdate();
        }
    }

    public static void updateClaimStatus(Integer claimId, ClaimStatus status) throws SQLException {
        String query = "UPDATE claims SET status = ? WHERE id_claim = ?";
        
        try (Connection conn = Connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, status.name());
            stmt.setInt(2, claimId);
            stmt.executeUpdate();
        }
    }
}