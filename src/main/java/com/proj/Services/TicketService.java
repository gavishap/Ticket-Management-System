package com.proj.Services;

import com.proj.Models.Ticket;
import com.proj.Models.User;
import com.proj.Models.Event;
import com.proj.Models.Seat;
import com.proj.DAO.TicketDAO;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TicketService {
    private TicketDAO ticketDAO;

    public TicketService() {
        this.ticketDAO = new TicketDAO();
    }

    public Ticket createTicket(Event event, User user, Seat seat) {
        Ticket ticket = new Ticket();
        ticket.setEvent(event);
        ticket.setUser(user);
        ticket.setSeat(seat);
        try {
            return ticketDAO.insertTicket(ticket);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Ticket getTicketById(int id) {
        try {
            return ticketDAO.getTicketById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Ticket updateTicket(int id, Event event, User user, Seat seat) {
        Ticket ticket = new Ticket();
        ticket.setId(id);
        ticket.setEvent(event);
        ticket.setUser(user);
        ticket.setSeat(seat);
        try {
            return ticketDAO.updateTicket(id, ticket);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteTicket(int id) {
        try {
            ticketDAO.deleteTicket(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Ticket> getTicketsByUserId(int userId) {
        try {
            return ticketDAO.getTicketsByUserId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
