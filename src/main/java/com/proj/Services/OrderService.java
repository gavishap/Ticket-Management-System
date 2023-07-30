package com.proj.Services;

import com.proj.Models.Order;
import com.proj.DAO.OrderDAO;
import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private OrderDAO orderDAO;

    public OrderService() {
        this.orderDAO = new OrderDAO();
    }

    public Order createOrder(Order order) {
        try {
            return orderDAO.insertOrder(order);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Order getOrderById(int id) {
        try {
            return orderDAO.getOrderById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Order updateOrder(int id, Order order) {
        try {
            return orderDAO.updateOrder(id, order);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteOrder(int id) {
        try {
            orderDAO.deleteOrder(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getOrdersByUserId(int userId) {
        try {
            return orderDAO.getOrdersByUserId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
