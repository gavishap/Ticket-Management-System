package com.proj.Models;

public class Venue {
    private int id;
    private String name;
    private String location;
    private int seatingCapacity;

    public Venue() {
    }

    public Venue(int id, String name, String location, int seatingCapacity) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.seatingCapacity = seatingCapacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }
}
