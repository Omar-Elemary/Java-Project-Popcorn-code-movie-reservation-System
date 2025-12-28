package com.movietickets.repository;

import com.movietickets.model.Admin;
import com.movietickets.util.JsonHandler;
import java.util.List;
import java.util.Optional;

public class AdminRepository {
    private final JsonHandler<Admin> jsonHandler;
    private static final String FILE_PATH = "data/admins.json";

    public AdminRepository() {
        this.jsonHandler = new JsonHandler<>(Admin.class, FILE_PATH);
    }

    public boolean add(Admin admin) {
        if (admin == null) {
            return false;
        }
        return jsonHandler.add(admin);
    }

    public boolean save(Admin admin) {
        return add(admin);
    }

    public Optional<Admin> findById(int adminId) {
        return jsonHandler.findFirst(admin -> admin.getId() == adminId);
    }

    public Optional<Admin> findByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return Optional.empty();
        }
        return jsonHandler.findFirst(admin -> 
            admin.getEmail() != null && 
            admin.getEmail().equalsIgnoreCase(email.trim())
        );
    }

    public List<Admin> getAll() {
        return jsonHandler.readAll();
    }

    public List<Admin> findAll() {
        return getAll();
    }

    public boolean update(Admin admin) {
        if (admin == null) {
            return false;
        }
        return jsonHandler.update(admin, a -> a.getId() == admin.getId());
    }

    public boolean deleteById(int adminId) {
        return jsonHandler.delete(admin -> admin.getId() == adminId);
    }

    public boolean delete(Admin admin) {
        if (admin == null) {
            return false;
        }
        return deleteById(admin.getId());
    }

    public boolean existsById(int adminId) {
        return findById(adminId).isPresent();
    }

    public boolean existsByEmail(String email) {
        return findByEmail(email).isPresent();
    }

    public int count() {
        return jsonHandler.size();
    }

    public long countLong() {
        return count();
    }
}