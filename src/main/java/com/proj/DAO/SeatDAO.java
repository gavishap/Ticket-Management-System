package com.proj.DAO;

import com.proj.Models.Seat;
import com.proj.Utils.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeatDAO {

    public Seat getSeatById(int id) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "SELECT * FROM Seat WHERE seatId=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Seat seat = new Seat();
            seat.setSeatId(resultSet.getInt("seatId"));
            seat.setSeatNumber(resultSet.getString("seatNumber"));
            seat.setVenueId(resultSet.getInt("venueId"));
            return seat;
        } else {
            return null;
        }
    }

    public Seat insertSeat(Seat seat) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "INSERT INTO Seat (seatNumber, venueId) VALUES (?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, seat.getSeatNumber());
        statement.setInt(2, seat.getVenueId());
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating seat failed, no rows affected.");
        }

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                seat.setSeatId(generatedKeys.getInt(1));
                return seat;
            } else {
                throw new SQLException("Creating seat failed, no ID obtained.");
            }
        }
    }

    public Seat updateSeat(int id, Seat seat) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "UPDATE Seat SET seatNumber=?, venueId=? WHERE seatId=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, seat.getSeatNumber());
        statement.setInt(2, seat.getVenueId());
        statement.setInt(3, id);
        statement.executeUpdate();

        return getSeatById(id);
    }

    public void deleteSeat(int id) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "DELETE FROM Seat WHERE seatId=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public List<Seat> getAllSeats() throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "SELECT * FROM Seat";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<Seat> seats = new ArrayList<>();
        while (resultSet.next()) {
            Seat seat = new Seat();
            seat.setSeatId(resultSet.getInt("seatId"));
            seat.setSeatNumber(resultSet.getString("seatNumber"));
            seat.setVenueId(resultSet.getInt("venueId"));
            seats.add(seat);
        }
        return seats;
    }
}
