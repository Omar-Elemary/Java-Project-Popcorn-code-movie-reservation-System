package com.movietickets.repository;

import com.movietickets.model.Hall;
import com.movietickets.util.JsonHandler;
import java.util.List;
import java.util.Optional;

public class HallRepository {
    private final JsonHandler<Hall> jsonHandler;
    private static final String FILE_PATH = "data/halls.json";

    public HallRepository() {
        this.jsonHandler = new JsonHandler<>(Hall.class, FILE_PATH);
    }

    public boolean add(Hall hall) {
        if (hall == null) {
            return false;
        }
        return jsonHandler.add(hall);
    }

    public boolean save(Hall hall) {
        return add(hall);
    }

    public Optional<Hall> getById(int hallId) {
        return jsonHandler.findFirst(hall -> hall.getHallID() == hallId);
    }

    public Optional<Hall> findById(int hallId) {
        return getById(hallId);
    }

    public List<Hall> getAll() {
        return jsonHandler.readAll();
    }

    public List<Hall> findAll() {
        return getAll();
    }

    public boolean update(Hall hall) {
        if (hall == null) {
            return false;
        }
        return jsonHandler.update(hall, h -> h.getHallID() == hall.getHallID());
    }

    public boolean delete(int hallId) {
        return jsonHandler.delete(hall -> hall.getHallID() == hallId);
    }

    public boolean deleteById(int hallId) {
        return delete(hallId);
    }

    public boolean delete(Hall hall) {
        if (hall == null) {
            return false;
        }
        return delete(hall.getHallID());
    }

    public boolean exists(int hallId) {
        return getById(hallId).isPresent();
    }

    public boolean existsById(int hallId) {
        return exists(hallId);
    }

    public int count() {
        return jsonHandler.size();
    }
}