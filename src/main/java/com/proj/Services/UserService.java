package com.proj.Services;

import com.proj.Models.User;
import com.proj.DAO.UserDAO;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public User createUser(User user) {
        try {
            return userDAO.insertUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserById(int id) {
        try {
            return userDAO.getUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        try {
            return userDAO.getUserByUsernameAndPassword(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User updateUser(int id, User user) {
        try {
            return userDAO.updateUser(id, user);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteUser(int id) {
        try {
            userDAO.deleteUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try {
            return userDAO.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User updateUserPartially(int id, User user) {
        try {
            return userDAO.updateUserPartially(id, user);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
