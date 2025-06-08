package org.oop.lostfound.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.oop.lostfound.model.User;

public class LoginDAO {
    private Connection connection; 

    public LoginDAO(Connection connection) {
        this.connection = connection;
    }

    public User checkLogin(String usernameOrEmail, String user_password) {
    String sql = "SELECT * FROM account WHERE (username = ? OR email = ?) AND user_password = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, usernameOrEmail);
        statement.setString(2, usernameOrEmail);
        statement.setString(3, user_password);
        try (ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
                return new User(rs.getInt("id_account"), rs.getString("username"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}


}