package com.movietickets.repository;

import com.movietickets.model.Reservation;
import com.movietickets.model.User;
import com.movietickets.util.JsonHandler;
import java.util.List;
import java.util.Optional;

public class ReservationRepository {
    private final JsonHandler<Reservation> jsonHandler;
    private static final String FILE_PATH = "data/reservations.json";

    public ReservationRepository() {
        this.jsonHandler = new JsonHandler<>(Reservation.class, FILE_PATH);
    }

    public boolean add(Reservation reservation) {
        if (reservation == null) {
            return false;
        }
        return jsonHandler.add(reservation);
    }

    public boolean save(Reservation reservation) {
        return add(reservation);
    }

    public Optional<Reservation> getById(int reservationId) {
        return jsonHandler.findFirst(reservation -> reservation.getReservationId() == reservationId);
    }

    public Optional<Reservation> findById(int reservationId) {
        return getById(reservationId);
    }

    public List<Reservation> findByUser(User user) {
        if (user == null) {
            return List.of();
        }
        return jsonHandler.find(reservation -> 
            reservation.getUser() != null && 
            reservation.getUser().equals(user)
        );
    }

    public List<Reservation> findByUserId(int userId) {
        return jsonHandler.find(reservation -> 
            reservation.getUser() != null && 
            reservation.getUser().getId() == userId
        );
    }
    
    public List<Reservation> findByStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            return List.of();
        }
        return jsonHandler.find(reservation -> 
            reservation.getStatus() != null && 
            reservation.getStatus().equalsIgnoreCase(status.trim())
        );
    }

    public List<Reservation> getAll() {
        return jsonHandler.readAll();
    }

    public List<Reservation> findAll() {
        return getAll();
    }

    public boolean update(Reservation reservation) {
        if (reservation == null) {
            return false;
        }
        return jsonHandler.update(reservation, r -> r.getReservationId() == reservation.getReservationId());
    }

    public boolean delete(int reservationId) {
        return jsonHandler.delete(reservation -> reservation.getReservationId() == reservationId);
    }

    public boolean deleteById(int reservationId) {
        return delete(reservationId);
    }

    public boolean delete(Reservation reservation) {
        if (reservation == null) {
            return false;
        }
        return delete(reservation.getReservationId());
    }

    public boolean exists(int reservationId) {
        return getById(reservationId).isPresent();
    }

    public boolean existsById(int reservationId) {
        return exists(reservationId);
    }

    public int count() {
        return jsonHandler.size();
    }
}