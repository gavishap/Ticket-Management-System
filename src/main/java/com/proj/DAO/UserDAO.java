package com.proj.DAO;

import com.proj.Models.User;
import com.proj.Utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public User insertUser(User user) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "INSERT INTO User (username, password) VALUES (?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }

        return user;
    }

    public User getUserById(int id) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "SELECT * FROM User WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            return user;
        } else {
            return null;
        }
    }

    public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "SELECT * FROM User WHERE username = ? AND password = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            return user;
        } else {
            return null;
        }
    }

    public User updateUser(int id, User user) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "UPDATE User SET username=?, password=? WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setInt(3, id);
        int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) {
            return null;
        }
        user.setId(id);
        return user;
    }

    public void deleteUser(int id) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "DELETE FROM User WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        Connection conn = DatabaseUtil.getConnection();
        String sql = "SELECT * FROM User";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            users.add(user);
        }
        return users;
    }

    public User updateUserPartially(int id, User user) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "UPDATE User SET username = COALESCE(?, username), password = COALESCE(?, password) WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setInt(3, id);
        int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) {
            return null;
        }
        return this.getUserById(id);
    }
}
