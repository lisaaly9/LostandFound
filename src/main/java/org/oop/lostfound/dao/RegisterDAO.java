package org.oop.lostfound.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterDAO {
    private Connection connection; 

    public RegisterDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean registerUser(String username, String phone_number, String email, String user_password) {
        String query = "INSERT INTO account(username, phone_number, email, user_password) VALUES(?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, phone_number);
            statement.setString(3, email);
            statement.setString(4, user_password);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
