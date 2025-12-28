package com.movietickets.repository;

import com.movietickets.model.Movie;
import com.movietickets.util.JsonHandler;
import java.util.List;
import java.util.Optional;

public class MovieRepository {
    private final JsonHandler<Movie> jsonHandler;
    private static final String FILE_PATH = "data/movies.json";

    public MovieRepository() {
        this.jsonHandler = new JsonHandler<>(Movie.class, FILE_PATH);
    }

    public boolean add(Movie movie) {
        if (movie == null) {
            return false;
        }
        return jsonHandler.add(movie);
    }

    public boolean save(Movie movie) {
        return add(movie);
    }

    public Optional<Movie> getById(int movieId) {
        return jsonHandler.findFirst(movie -> movie.getMovieId() == movieId);
    }

    public Optional<Movie> findById(int movieId) {
        return getById(movieId);
    }

    public List<Movie> findByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return List.of();
        }
        String searchTitle = title.toLowerCase().trim();
        return jsonHandler.find(movie -> 
            movie.getTitle() != null && 
            movie.getTitle().toLowerCase().contains(searchTitle)
        );
    }

    public List<Movie> findByCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            return List.of();
        }
        return jsonHandler.find(movie -> 
            movie.getCategory() != null && 
            movie.getCategory().equalsIgnoreCase(category.trim())
        );
    }

    public List<Movie> findByMinRating(double minRating) {
        return jsonHandler.find(movie -> movie.getRating() >= minRating);
    }

    public List<Movie> getAll() {
        return jsonHandler.readAll();
    }

    public List<Movie> findAll() {
        return getAll();
    }

    public boolean update(Movie movie) {
        if (movie == null) {
            return false;
        }
        return jsonHandler.update(movie, m -> m.getMovieId() == movie.getMovieId());
    }

    public boolean delete(int movieId) {
        return jsonHandler.delete(movie -> movie.getMovieId() == movieId);
    }

    public boolean deleteById(int movieId) {
        return delete(movieId);
    }
    
    public boolean delete(Movie movie) {
        if (movie == null) {
            return false;
        }
        return delete(movie.getMovieId());
    }
      
    public boolean exists(int movieId) {
        return getById(movieId).isPresent();
    }

    public boolean existsById(int movieId) {
        return exists(movieId);
    }

    public int count() {
        return jsonHandler.size();
    }
}