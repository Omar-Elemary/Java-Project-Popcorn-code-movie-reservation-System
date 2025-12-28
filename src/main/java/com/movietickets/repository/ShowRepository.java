package com.movietickets.repository;

import com.movietickets.model.Show;
import com.movietickets.util.JsonHandler;
import java.util.List;
import java.util.Optional;

public class ShowRepository {
    private final JsonHandler<Show> jsonHandler;
    private static final String FILE_PATH = "data/shows.json";

    public ShowRepository() {
        this.jsonHandler = new JsonHandler<>(Show.class, FILE_PATH);
    }

    public boolean add(Show show) {
        if (show == null) {
            return false;
        }
        return jsonHandler.add(show);
    }

    public boolean save(Show show) {
        return add(show);
    }

    public Optional<Show> getById(int showId) {
        return jsonHandler.findFirst(show -> show.getShowID() == showId);
    }

    public Optional<Show> findById(int showId) {
        return getById(showId);
    }

    public List<Show> findByMovieId(int movieId) {
        return jsonHandler.find(show -> 
            show.getMovie() != null && 
            show.getMovie().getMovieId() == movieId
        );
    }
    
    public List<Show> findByHallId(int hallId) {
        return jsonHandler.find(show -> 
            show.getHallID() != null && 
            show.getHallID().getHallID() == hallId
        );
    }
    
    public List<Show> findByDate(String date) {
        if (date == null || date.trim().isEmpty()) {
            return List.of();
        }
        return jsonHandler.find(show -> 
            show.getDate() != null && 
            show.getDate().equals(date.trim())
        );
    }

    public List<Show> getAll() {
        return jsonHandler.readAll();
    }

    public List<Show> findAll() {
        return getAll();
    }

    public boolean update(Show show) {
        if (show == null) {
            return false;
        }
        return jsonHandler.update(show, s -> s.getShowID() == show.getShowID());
    }

    public boolean delete(int showId) {
        return jsonHandler.delete(show -> show.getShowID() == showId);
    }

    public boolean deleteById(int showId) {
        return delete(showId);
    }

    public boolean delete(Show show) {
        if (show == null) {
            return false;
        }
        return delete(show.getShowID());
    }

    public boolean exists(int showId) {
        return getById(showId).isPresent();
    }

    public boolean existsById(int showId) {
        return exists(showId);
    }

    public int count() {
        return jsonHandler.size();
    }
}