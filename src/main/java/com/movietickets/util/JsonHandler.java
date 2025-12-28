package com.movietickets.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Improved JSON handler for data persistence with better error handling and efficiency.
 * Provides CRUD operations with automatic file management.
 * @param <T> The type of objects to handle
 */
public class JsonHandler<T> {
    private final Gson gson;
    private final String filePath;
    private final Type listType;
    private List<T> data;
    private boolean isLoaded = false;

    /**
     * Constructor initializes the JSON handler
     * @param clazz The class type for deserialization
     * @param filePath The path to the JSON file
     */
    public JsonHandler(Class<T> clazz, String filePath) {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        this.filePath = filePath;
        this.listType = TypeToken.getParameterized(List.class, clazz).getType();
        this.data = new ArrayList<>();
        
        // Ensure data directory exists
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        
        // Load data on initialization
        loadData();
    }

    /**
     * Loads data from the JSON file
     */
    private void loadData() {
        try (FileReader reader = new FileReader(filePath)) {
            List<T> loadedData = gson.fromJson(reader, listType);
            this.data = loadedData != null ? loadedData : new ArrayList<>();
            this.isLoaded = true;
        } catch (Exception e) {
            // Any parsing or IO issue -> start with empty list and overwrite on next save
            this.data = new ArrayList<>();
            this.isLoaded = true;
        }
    }

    /**
     * Saves data to the JSON file
     */
    private void saveData() {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            System.err.println("Error saving data to " + filePath + ": " + e.getMessage());
        }
    }

    /**
     * Adds a new item to the collection
     * @param item The item to add
     * @return true if added successfully
     */
    public boolean add(T item) {
        if (item == null) {
            return false;
        }
        
        if (!isLoaded) {
            loadData();
        }
        
        if (!data.contains(item)) {
            data.add(item);
            saveData();
            return true;
        }
        return false;
    }

    /**
     * Gets all items
     * @return List of all items
     */
    public List<T> readAll() {
        if (!isLoaded) {
            loadData();
        }
        return new ArrayList<>(data);
    }

    /**
     * Finds the first item matching the predicate
     * @param predicate The search condition
     * @return Optional containing the found item
     */
    public Optional<T> findFirst(Predicate<T> predicate) {
        if (!isLoaded) {
            loadData();
        }
        return data.stream().filter(predicate).findFirst();
    }

    /**
     * Finds all items matching the predicate
     * @param predicate The search condition
     * @return List of matching items
     */
    public List<T> find(Predicate<T> predicate) {
        if (!isLoaded) {
            loadData();
        }
        return data.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * Updates an item in the collection
     * @param item The item to update
     * @param predicate The condition to find the item to replace
     * @return true if updated successfully
     */
    public boolean update(T item, Predicate<T> predicate) {
        if (item == null) {
            return false;
        }
        
        if (!isLoaded) {
            loadData();
        }
        
        for (int i = 0; i < data.size(); i++) {
            if (predicate.test(data.get(i))) {
                data.set(i, item);
                saveData();
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes items matching the predicate
     * @param predicate The condition to find items to delete
     * @return true if any items were deleted
     */
    public boolean delete(Predicate<T> predicate) {
        if (!isLoaded) {
            loadData();
        }
        
        boolean removed = data.removeIf(predicate);
        if (removed) {
            saveData();
        }
        return removed;
    }

    /**
     * Gets the count of items
     * @return Number of items
     */
    public int size() {
        if (!isLoaded) {
            loadData();
        }
        return data.size();
    }

    /**
     * Checks if the collection is empty
     * @return true if empty
     */
    public boolean isEmpty() {
        if (!isLoaded) {
            loadData();
        }
        return data.isEmpty();
    }

    /**
     * Clears all data
     */
    public void clear() {
        data.clear();
        saveData();
    }

    /**
     * Forces a reload of data from file
     */
    public void reload() {
        loadData();
    }
}