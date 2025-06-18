package org.oop.lostfound.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.oop.lostfound.model.Admin;

public class LoginAdminDAO
{
    private Connection connection;

    public LoginAdminDAO(Connection connection)
    {
        this.connection = connection;
    }

    public Admin checkLogin(String usernameOrEmail, String user_password)
    {
        String sql = "SELECT * FROM admin WHERE (username = ? OR email = ?) AND user_password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setString(1, usernameOrEmail);
            stmt.setString(2, usernameOrEmail);
            stmt.setString(3, user_password);
            try (ResultSet rs = stmt.executeQuery())
            {
                if (rs.next())
                {
                    return new Admin(
                            rs.getInt("id_admin"),
                            rs.getString("username"),
                            rs.getString("phone_number"),
                            rs.getString("email"),
                            rs.getString("user_password"),
                            rs.getInt("admin_level")
                    );
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}