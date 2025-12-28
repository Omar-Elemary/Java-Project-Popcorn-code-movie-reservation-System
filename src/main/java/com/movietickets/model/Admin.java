package com.movietickets.model;

import java.util.concurrent.atomic.AtomicInteger;

import com.movietickets.repository.MovieRepository;
import com.movietickets.repository.ShowRepository;

public class Admin implements Person {

    // ID generator for admins (starts from 1000)
    private static final AtomicInteger idGenerator = new AtomicInteger(1000);

    private final int id;
    private String email;

    public Admin(String email) {
        this.id = idGenerator.getAndIncrement();
        this.email = email;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public boolean isAdmin() {
        return true;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private void verifyAdminAccess() {
        if (id <= 1000 || id >= 2000) {
            System.out.println("Access denied. ID " + id + " is not authorized as Admin.");
        }
    }

    public void addMovie(Movie movie, MovieRepository repo) {
        verifyAdminAccess();
        repo.add(movie);
        System.out.println("Admin " + id + " added movie: " + movie.getTitle());
    }

    public void removeMovie(int movieId, MovieRepository repo) {
        verifyAdminAccess();
        repo.delete(movieId);
        System.out.println("Admin " + id + " removed movie with ID " + movieId);
    }

    public void updateShow(Show show, ShowRepository repo) {
        verifyAdminAccess();
        repo.update(show);
        System.out.println("Admin " + id + " updated show " + show.getShowID());
    }

    public String generateReport(Show show) {
        verifyAdminAccess();
        return String.format("Admin %d report for Show %d: occupancy = %.2f%%",
                id, show.getShowID(), show.occupancyRate());
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
