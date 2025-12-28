package com.movietickets.repository;

import com.movietickets.model.Seat;
import com.movietickets.util.JsonHandler;
import java.util.List;
import java.util.Optional;


public class SeatRepository {
    private final JsonHandler<Seat> jsonHandler;
    private static final String FILE_PATH = "data/seats.json";

    public SeatRepository() {
        this.jsonHandler = new JsonHandler<>(Seat.class, FILE_PATH);
    }

    public boolean save(Seat seat) {
        if (seat == null) {
            return false;
        }
        return jsonHandler.add(seat);
    }

    public Optional<Seat> findById(int seatId) {
        return jsonHandler.findFirst(seat -> seat.getSeatID() == seatId);
    }

    public List<Seat> findByHallId(int hallId) {
        return jsonHandler.find(seat -> 
            seat.getHall() != null && 
            seat.getHall().getHallID() == hallId
        );
    }

    public List<Seat> findByRow(String row) {
        if (row == null || row.trim().isEmpty()) {
            return List.of();
        }
        return jsonHandler.find(seat -> 
            seat.getRow() != null && 
            seat.getRow().equalsIgnoreCase(row.trim())
        );
    }

    public List<Seat> findByStatus(boolean available) {
        return jsonHandler.find(seat -> seat.getStatus() == available);
    }

    public List<Seat> findAvailable() {
        return jsonHandler.find(Seat::isAvailable);
    }

    public List<Seat> findAvailableByHallId(int hallId) {
        return jsonHandler.find(seat -> 
            seat.getHall() != null && 
            seat.getHall().getHallID() == hallId &&
            seat.isAvailable()
        );
    }

    public List<Seat> findByPriceRange(double minPrice, double maxPrice) {
        return jsonHandler.find(seat -> 
            seat.getPrice() >= minPrice && 
            seat.getPrice() <= maxPrice
        );
    }

    public List<Seat> findAll() {
        return jsonHandler.readAll();
    }

    public boolean update(Seat seat) {
        if (seat == null) {
            return false;
        }
        return jsonHandler.update(seat, s -> s.getSeatID() == seat.getSeatID());
    }

    public boolean deleteById(int seatId) {
        return jsonHandler.delete(seat -> seat.getSeatID() == seatId);
    }

    public boolean delete(Seat seat) {
        if (seat == null) {
            return false;
        }
        return deleteById(seat.getSeatID());
    }

    public boolean existsById(int seatId) {
        return findById(seatId).isPresent();
    }

    public long count() {
        return jsonHandler.readAll().size();
    }

    public long countAvailable() {
        return findAvailable().size();
    }

    public long countByStatus(boolean available) {
        return findByStatus(available).size();
    }
    
}