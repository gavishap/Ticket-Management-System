package com.proj.Models;

public class Ticket {
    private int id;
    private Event event;
    private User user;
    private Seat seat;

    // getter methods
    public int getId() {
        return id;
    }

    public Event getEvent() {
        return event;
    }

    public User getUser() {
        return user;
    }

    public Seat getSeat() {
        return seat;
    }

    // setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
