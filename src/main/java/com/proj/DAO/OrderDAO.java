package com.proj.DAO;

import java.util.List;
import java.util.ArrayList;
import com.proj.Models.Order;
import com.proj.Utils.DatabaseUtil;
import java.sql.*;

public class OrderDAO {

    public Order getOrderById(int id) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "SELECT * FROM OrderTable WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Order order = mapToOrder(resultSet);
            return order;
        } else {
            return null;
        }
    }

    public Order insertOrder(Order order) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "INSERT INTO OrderTable (userId, ticketId, date) VALUES (?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, order.getUserId());
        statement.setInt(2, order.getTicketId());
        statement.setString(3, order.getDate());
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating order failed, no rows affected.");
        }

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                order.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating order failed, no ID obtained.");
            }
        }

        return order;
    }

    public Order updateOrder(int id, Order order) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "UPDATE OrderTable SET userId=?, ticketId=?, date=? WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, order.getUserId());
        statement.setInt(2, order.getTicketId());
        statement.setString(3, order.getDate());
        statement.setInt(4, id);
        statement.executeUpdate();

        return getOrderById(id);
    }

    public void deleteOrder(int id) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "DELETE FROM OrderTable WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    private Order mapToOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setUserId(resultSet.getInt("userId"));
        order.setTicketId(resultSet.getInt("ticketId"));
        order.setDate(resultSet.getString("date"));
        return order;
    }

    public List<Order> getOrdersByUserId(int userId) throws SQLException {
        List<Order> orders = new ArrayList<>();
        Connection conn = DatabaseUtil.getConnection();
        String sql = "SELECT * FROM OrderTable WHERE userId=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Order order = mapToOrder(resultSet);
            orders.add(order);
        }
        return orders;
    }

}
