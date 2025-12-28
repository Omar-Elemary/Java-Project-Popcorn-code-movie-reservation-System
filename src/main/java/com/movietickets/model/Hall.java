package com.movietickets.model;

import java.util.ArrayList;
import java.util.List;


public class Hall {
    private int hallID;
    private int capacity;
    private List<Seat> seats;
    private transient Cinema cinema;            

    public Hall() {
        this.seats = new ArrayList<>();
    }

    public Hall(int hallID, int capacity, Cinema cinema) {
        this.hallID = hallID;
        this.capacity = capacity;
        this.cinema = cinema;
        this.seats = new ArrayList<>();
    }

    public int getHallID() {
        return hallID;
    }

    public void setHallID(int hallID) {
        this.hallID = hallID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }


    public void addSeat(Seat seat) {
        if (seat != null && !seats.contains(seat)) {
            seats.add(seat);
        }
    }

    public void removeSeat(Seat seat) {
        seats.remove(seat);
    }
    
    
    public int getAvailableSeatsCount() {
        int count = 0;
        for (Seat seat : seats) {
            if (seat.isAvailable()) {
                count++;
            }
        }
        return count;
    }
    
    @Override
    public String toString() {
        return "Hall{" +
                "hallID=" + hallID +
                ", capacity=" + capacity +
                ", seatsNo=" + seats.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Hall )) return false;
        Hall hall = (Hall) o;
        return hallID == hall.hallID;
    }
    
    
    @Override
    public int hashCode() {
        return java.util.Objects.hash(hallID);
    }
}




