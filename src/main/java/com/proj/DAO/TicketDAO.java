package com.proj.DAO;

import com.proj.Models.Ticket;
import com.proj.Models.User;
import com.proj.Models.Event;
import com.proj.Models.Seat;
import com.proj.Utils.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    public Ticket getTicketById(int id) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "SELECT * FROM Ticket WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Ticket ticket = mapToTicket(resultSet);
            return ticket;
        } else {
            return null;
        }
    }

    public Ticket insertTicket(Ticket ticket) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "INSERT INTO Ticket (eventId, userId, seatId) VALUES (?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, ticket.getEvent().getId());
        statement.setInt(2, ticket.getUser().getId());
        statement.setInt(3, ticket.getSeat().getSeatId());
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating ticket failed, no rows affected.");
        }

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                ticket.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating ticket failed, no ID obtained.");
            }
        }

        return ticket;
    }

    public Ticket updateTicket(int id, Ticket ticket) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "UPDATE Ticket SET eventId=?, userId=?, seatId=? WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, ticket.getEvent().getId());
        statement.setInt(2, ticket.getUser().getId());
        statement.setInt(3, ticket.getSeat().getSeatId());
        statement.setInt(4, id);
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Updating ticket failed, no rows affected.");
        }

        return getTicketById(id);
    }

    public void deleteTicket(int id) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "DELETE FROM Ticket WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Deleting ticket failed, no rows affected.");
        }
    }

    private Ticket mapToTicket(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket();
        EventDAO eventDAO = new EventDAO();
        UserDAO userDAO = new UserDAO();
        SeatDAO seatDAO = new SeatDAO();

        ticket.setId(resultSet.getInt("id"));
        ticket.setEvent(eventDAO.getEventById(resultSet.getInt("eventId")));
        ticket.setUser(userDAO.getUserById(resultSet.getInt("userId")));
        ticket.setSeat(seatDAO.getSeatById(resultSet.getInt("seatId")));
        return ticket;
    }

    public List<Ticket> getTicketsByUserId(int userId) throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        Connection conn = DatabaseUtil.getConnection();
        String sql = "SELECT * FROM Ticket WHERE userId=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Ticket ticket = mapToTicket(resultSet);
            tickets.add(ticket);
        }
        return tickets;
    }
}
