package com.movietickets.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents a reservation made by a user for a show
 */


public class Reservation {
    // ID generator for reservations (starts from 3000)
    private static final AtomicInteger idGenerator = new AtomicInteger(3000);
    
    private final int reservationId;
    private User user;
    private Show show;
    private List<Integer> seats;
    private String reservationTime;
    private String status;                          // "ACTIVE", "CANCELLED", "COMPLETED"
    
    public Reservation(User user, Show show, List<Integer> seats) {
        this.reservationId = idGenerator.getAndIncrement();
        this.user = user;
        this.show = show;
        this.seats = seats;
        this.reservationTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.status = "ACTIVE";
    }
    
    // Getters and setters
    public int getReservationId() {
        return reservationId;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Show getShow() {
        return show;
    }
    
    public void setShow(Show show) {
        this.show = show;
    }
    
    public List<Integer> getSeats() {
        return seats;
    }
    
    public void setSeats(List<Integer> seats) {
        this.seats = seats;
    }
    
    public String getReservationTime() {
        return reservationTime;
    }
    
    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * Cancels this reservation and releases the seats
     */
    public void cancel() {
        if ("ACTIVE".equals(status)) {
            this.status = "CANCELLED";
            // Seats are tracked by IDs per show; nothing to release globally
            System.out.println("Reservation " + reservationId + " has been cancelled");
        }
    }
    
    /**
     * Completes this reservation (after payment)
     */
    public void complete() {
        if ("ACTIVE".equals(status)) {
            this.status = "COMPLETED";
            System.out.println("Reservation " + reservationId + " has been completed");
        }
    }
    
    /**
     * Calculates the total price for this reservation
     */
    
    public double getTotalPrice() {
        if (seats == null || seats.isEmpty()) {
            return 0.0;
        }
        return seats.size() * 15.0;
    }
    
    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", user=" + (user != null ? user.getEmail() : "N/A") +
                ", show=" + (show != null ? show.getShowID() : "N/A") +
                ", seats=" + seats.size() +
                ", status='" + status + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Reservation)) return false;
        Reservation r = (Reservation) o;
        return reservationId == r.reservationId;
    }
    
    @Override
    public int hashCode() {
        return java.util.Objects.hash(reservationId);
    }
}
