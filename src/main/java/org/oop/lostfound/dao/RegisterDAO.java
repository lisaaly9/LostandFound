package org.oop.lostfound.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterDAO
{
    private Connection connection; 

    public RegisterDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean registerUser(String username, String phone_number, String email, String user_password) {
        String query = "INSERT INTO account(username, phone_number, email, user_password) VALUES(?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query))
        {
            stmt.setString(1, username);
            stmt.setString(2, phone_number);
            stmt.setString(3, email);
            stmt.setString(4, user_password);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isUsernameTaken(String username) {
        String query = "SELECT username FROM account WHERE username = ? UNION SELECT username FROM admin WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, username);
            return stmt.executeQuery().next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
