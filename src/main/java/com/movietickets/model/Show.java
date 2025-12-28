package com.movietickets.model;

import javafx.scene.control.Tooltip;

import java.util.ArrayList;
import java.util.List;


public class Show {
    private int showID;
    private String startTime;
    private String date;
    private Movie movieID;
    private Hall hallID;
    private List<Integer> seatIDs;                  // reserved seat IDs for this show


    public Show(int showID, String startTime, String date, Movie movieID, Hall hallID) {
        this.showID = showID;
        this.startTime = startTime;
        this.date = date;
        this.movieID = movieID;
        this.hallID = hallID;
        this.seatIDs = new ArrayList<>();
    }

    public int getShowID() {
        return showID;
    }

    public void setShowID(int showID) {
        this.showID = showID;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }


    public void setDate(String date) {
        this.date = date;
    }

    public String getDate(){
        return date;
    }

    public void setMovieID(Movie movieID) {
        this.movieID = movieID;
    }

    /**
     * Gets the movieID for this show
     * @return the movie object
    */
    
   
    public Movie getMovie() {
        return movieID;
    }

    
    public Hall getHallID() {
        return hallID;
    }

    public void setHallID(Hall hallID) {
        this.hallID = hallID;
    }

    public List<Integer> getSeatIDs() {
        return seatIDs;
    }

    public void setSeatIDs(List<Integer> seatIDs) {
        this.seatIDs = seatIDs;
    }
    

    public boolean checkAvailable(List<Integer> seatIdsToReserved) {
        if (seatIdsToReserved == null || seatIdsToReserved.isEmpty()) {
            return false;
        }
        for (Integer id : seatIdsToReserved) {
            if (this.seatIDs.contains(id)) {
                return false; 
            }
        }
        return true;
    }


    public boolean reserveSeats(Reservation reservation, List<Integer> seatIdsToReserved) {
        if (reservation == null || seatIdsToReserved == null || seatIdsToReserved.isEmpty()) {
            return false;
        }

        if (!checkAvailable(seatIdsToReserved)) {
            return false;
        }

        this.seatIDs.addAll(seatIdsToReserved);
        return true;
    }
    
    /**
     * Calculates the occupancy rate of this show
     * @return occupancy rate as a percentage (0.0 to 100.0)
     */
    
    
    public double occupancyRate() {
        
        if (seatIDs.isEmpty()) {
            return 0.0;
        }

        int reservedSeats = seatIDs.size();
        int totalCapacity = hallID != null ? hallID.getCapacity() : seatIDs.size();
        if (totalCapacity <= 0) {
            totalCapacity = seatIDs.size();
        }
        return (double) reservedSeats / totalCapacity * 100.0;
    }
    
}

