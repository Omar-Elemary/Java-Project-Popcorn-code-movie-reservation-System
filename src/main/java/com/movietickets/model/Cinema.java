package com.movietickets.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cinema {
    private String name;
    private String address;
    private List<Hall> halls;

    public Cinema() {
        this.halls = new ArrayList<>();
    }

    public Cinema(String name, String address) {
        this.name = name;
        this.address = address;
        this.halls = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Hall> getHalls() {
        return halls;
    }

    public void setHalls(List<Hall> halls) {
        this.halls = halls;
    }
    
    
    public void addHall(Hall hall) {
        if (hall != null && !halls.contains(hall)) {
            halls.add(hall);
        }
    }
    
    public void removeHall(Hall hall) {
        halls.remove(hall);
    }
    
    @Override
    public String toString() {
        return "Cinema{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", hallNumbers=" + halls.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || ! (o instanceof Cinema) )return false;
        Cinema cinema = (Cinema) o;
        
        return name.equals(cinema.name) && address.equals( cinema.address);
    }
    
    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, address);
    }
}

