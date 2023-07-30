package com.proj.Models;

public class Seat {
    private int seatId;
    private String seatNumber;
    private int venueId;

    // getter methods
    public int getSeatId() {
        return seatId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public int getVenueId() {
        return venueId;
    }

    // setter methods
    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }
}
