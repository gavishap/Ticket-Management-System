package com.proj.Services;

import com.proj.Models.Venue;
import com.proj.DAO.VenueDAO;
import java.sql.SQLException;

public class VenueService {
    private VenueDAO venueDAO;

    public VenueService() {
        this.venueDAO = new VenueDAO();
    }

    public Venue createVenue(Venue venue) {
        try {
            return venueDAO.insertVenue(venue);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Venue getVenueById(int id) {
        try {
            return venueDAO.getVenueById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Venue updateVenue(int id, Venue venue) {
        try {
            return venueDAO.updateVenue(id, venue);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteVenue(int id) {
        try {
            venueDAO.deleteVenue(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
