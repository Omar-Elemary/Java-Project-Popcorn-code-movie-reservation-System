package com.movietickets.repository;

import com.movietickets.model.User;
import com.movietickets.util.JsonHandler;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private final JsonHandler<User> jsonHandler;
    private static final String FILE_PATH = "data/users.json";

    public UserRepository() {
        this.jsonHandler = new JsonHandler<>(User.class, FILE_PATH);
    }

    public boolean add(User user) {
        if (user == null) {
            return false;
        }
        return jsonHandler.add(user);
    }

    public boolean save(User user) {
        return add(user);
    }

    public Optional<User> getById(int userId) {
        return jsonHandler.findFirst(user -> user.getId() == userId);
    }

    public Optional<User> findById(int userId) {
        return getById(userId);
    }

    public Optional<User> findByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return Optional.empty();
        }
        return jsonHandler.findFirst(user -> 
            user.getEmail() != null && 
            user.getEmail().equalsIgnoreCase(email.trim())
        );
    }

    public List<User> getAll() {
        return jsonHandler.readAll();
    }

    public List<User> findAll() {
        return getAll();
    }

    public boolean update(User user) {
        if (user == null) {
            return false;
        }
        return jsonHandler.update(user, u -> u.getId() == user.getId());
    }

    public boolean delete(int userId) {
        return jsonHandler.delete(user -> user.getId() == userId);
    }

    public boolean deleteById(int userId) {
        return delete(userId);
    }

    public boolean delete(User user) {
        if (user == null) {
            return false;
        }
        return delete(user.getId());
    }

    public boolean exists(int userId) {
        return getById(userId).isPresent();
    }

    public boolean existsById(int userId) {
        return exists(userId);
    }

    public boolean existsByEmail(String email) {
        return findByEmail(email).isPresent();
    }

    public int count() {
        return jsonHandler.size();
    }
}