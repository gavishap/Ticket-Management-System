package com.proj.DAO;

import com.proj.Models.Event;
import com.proj.Utils.DatabaseUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EventDAO {

    public Event getEventById(int id) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "SELECT * FROM Event WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Event event = new Event();
            event.setId(resultSet.getInt("id"));
            event.setName(resultSet.getString("name"));
            event.setLocation(resultSet.getString("location"));
            event.setDate(resultSet.getString("date"));
            event.setVenueId((resultSet.getObject("venueId") != null) ? resultSet.getInt("venueId") : null);
            return event;
        } else {
            return null;
        }
    }

    public Event insertEvent(Event event) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "INSERT INTO Event (name, location, date, venueId) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, event.getName());
        statement.setString(2, event.getLocation());
        statement.setString(3, event.getDate());
        statement.setObject(4, event.getVenueId(), Types.INTEGER);
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating event failed, no rows affected.");
        }

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                event.setId(generatedKeys.getInt(1));
                return event;
            } else {
                throw new SQLException("Creating event failed, no ID obtained.");
            }
        }
    }

    public Event updateEvent(int id, Event event) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "UPDATE Event SET name=?, location=?, date=?, venueId=? WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, event.getName());
        statement.setString(2, event.getLocation());
        statement.setString(3, event.getDate());
        statement.setObject(4, event.getVenueId(), Types.INTEGER);
        statement.setInt(5, id);
        statement.executeUpdate();

        return getEventById(id);
    }

    public void deleteEvent(int id) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "DELETE FROM Event WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public List<Event> getAllEvents() throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "SELECT * FROM Event";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<Event> events = new ArrayList<>();
        while (resultSet.next()) {
            Event event = new Event();
            event.setId(resultSet.getInt("id"));
            event.setName(resultSet.getString("name"));
            event.setLocation(resultSet.getString("location"));
            event.setDate(resultSet.getString("date"));
            // event.setVenueId((resultSet.getObject("venueId") != null) ?
            // resultSet.getInt("venueId") : null);
            events.add(event);
        }
        return events;
    }

    public Event updateEventPartially(int id, Event event) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "UPDATE Event SET name=?, location=?, date=?, venueId=? WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);

        if (event.getName() != null) {
            statement.setString(1, event.getName());
        } else {
            statement.setNull(1, Types.VARCHAR);
        }
        if (event.getLocation() != null) {
            statement.setString(2, event.getLocation());
        } else {
            statement.setNull(2, Types.VARCHAR);
        }
        if (event.getDate() != null) {
            statement.setString(3, event.getDate());
        } else {
            statement.setNull(3, Types.VARCHAR);
        }
        if (event.getVenueId() != null) {
            statement.setInt(4, event.getVenueId());
        } else {
            statement.setNull(4, Types.INTEGER);
        }
        statement.setInt(5, id);
        statement.executeUpdate();

        return getEventById(id);
    }
}
