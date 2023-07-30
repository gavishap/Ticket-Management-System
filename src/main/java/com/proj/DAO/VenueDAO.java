package com.proj.DAO;

import com.proj.Models.Venue;
import com.proj.Utils.DatabaseUtil;
import java.sql.*;

public class VenueDAO {

    public Venue getVenueById(int id) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "SELECT * FROM Venue WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Venue venue = new Venue();
            venue.setId(resultSet.getInt("id"));
            venue.setName(resultSet.getString("name"));
            venue.setLocation(resultSet.getString("location"));
            venue.setSeatingCapacity(resultSet.getInt("seatingCapacity"));
            return venue;
        } else {
            return null;
        }
    }

    public Venue insertVenue(Venue venue) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "INSERT INTO Venue (name, location, seatingCapacity) VALUES (?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, venue.getName());
        statement.setString(2, venue.getLocation());
        statement.setInt(3, venue.getSeatingCapacity());
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating venue failed, no rows affected.");
        }

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                venue.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating venue failed, no ID obtained.");
            }
        }

        return venue;
    }

    public Venue updateVenue(int id, Venue venue) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "UPDATE Venue SET name=?, location=?, seatingCapacity=? WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, venue.getName());
        statement.setString(2, venue.getLocation());
        statement.setInt(3, venue.getSeatingCapacity());
        statement.setInt(4, id);
        statement.executeUpdate();

        return getVenueById(id);
    }

    public void deleteVenue(int id) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "DELETE FROM Venue WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }
}
