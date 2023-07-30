package com.proj.Models;

import java.util.List;

public class Event {
    private int id;
    private String name;
    private String location;
    private String date;
    private Integer venueId;

    // getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public Integer getVenueId() {
        return venueId;
    }

    // setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", date=" + date +
                ", venueId=" + venueId +
                '}';
    }

    public static void printEventsAsTable(List<Event> events) {
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%5s | %20s | %20s | %10s | %10s\n", "Id", "Name", "Location", "Date", "Venue Id");
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------");
        for (Event event : events) {
            System.out.printf("%5d | %20s | %20s | %10s | %10s\n", event.getId(), event.getName(), event.getLocation(),
                    event.getDate(), event.getVenueId());
        }
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------");
    }

}
