package com.proj.Models;

public class Order {
    private int id;
    private int userId;
    private int ticketId;
    private String date; // consider using java.time.LocalDate instead

    // getter methods
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public String getDate() {
        return date;
    }

    // setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
