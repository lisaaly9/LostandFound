package org.oop.lostfound.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterAdminDAO
{
    private Connection conn;

    public RegisterAdminDAO(Connection conn)
    {
        this.conn = conn;
    }

    public boolean registerAdmin(String username, String phone_number, String email, String user_password) {
        try {
            String sql = "INSERT INTO admin (username, phone_number, email, user_password, admin_level) VALUES (?, ?, ?, ?, 1)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, phone_number);
            stmt.setString(3, email);
            stmt.setString(4, user_password);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}