package org.oop.lostfound.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    private Connection connection; 

    public LoginDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean checkLogin(String usernameOrEmail, String user_password) {
    String sql = "SELECT * FROM account WHERE (username = ? OR email = ?) AND user_password = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, usernameOrEmail); // untuk username
        statement.setString(2, usernameOrEmail); // untuk email
        statement.setString(3, user_password);
        ResultSet rs = statement.executeQuery();
        boolean found = rs.next();
        return found;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
        }
    }

}