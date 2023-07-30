package com.proj.Services;

import com.proj.Models.Event;
import com.proj.DAO.EventDAO;
import java.sql.SQLException;
import java.util.List;

public class EventService {
    private EventDAO eventDAO;

    public EventService() {
        this.eventDAO = new EventDAO();
    }

    public Event createEvent(Event event) {
        try {
            return eventDAO.insertEvent(event);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Event getEventById(int id) {
        try {
            return eventDAO.getEventById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Event updateEvent(int id, Event event) {
        try {
            return eventDAO.updateEvent(id, event);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteEvent(int id) {
        try {
            eventDAO.deleteEvent(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Event> getAllEvents() {
        try {
            return eventDAO.getAllEvents();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Event updateEventPartially(int id, Event event) {
        try {
            return eventDAO.updateEventPartially(id, event);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
