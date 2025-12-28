package com.movietickets.model;

/**
 * Interface for all persons in the system.
 * Provides common structure for users and admins.
 */
public interface Person {
    /**
     * @return numeric ID (pattern range distinguishes role)
     */
    int getId();

    /**
     * @return email of the person
     */
    String getEmail();

    /**
     * @return true if this person is an admin
     */
    boolean isAdmin();
}
