package com.movietickets.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents a normal user of the system.
 */
public class User implements Person {

    // ID generator for users (starts from 2000)
    private static final AtomicInteger idGenerator = new AtomicInteger(2000);

    private final int id;
    private String email;

    public User(String email) {
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
        return false;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Example of user-specific method
    public void bookTicket() {
        System.out.println("User " + id + " booked a ticket.");
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
