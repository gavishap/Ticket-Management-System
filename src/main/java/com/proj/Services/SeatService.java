package com.proj.Services;

import com.proj.Models.Seat;
import com.proj.DAO.SeatDAO;
import java.sql.SQLException;
import java.util.List;

public class SeatService {
    private SeatDAO seatDAO;

    public SeatService() {
        this.seatDAO = new SeatDAO();
    }

    public Seat createSeat(Seat seat) {
        try {
            return seatDAO.insertSeat(seat);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Seat getSeatById(int id) {
        try {
            return seatDAO.getSeatById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Seat updateSeat(int id, Seat seat) {
        try {
            return seatDAO.updateSeat(id, seat);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteSeat(int id) {
        try {
            seatDAO.deleteSeat(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Seat> getAllSeats() {
        try {
            return seatDAO.getAllSeats();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
