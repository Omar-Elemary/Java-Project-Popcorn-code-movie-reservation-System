package com.movietickets.ui.Controller;

import com.movietickets.model.Reservation;

public class ReservationSessionController {
    private static Reservation currentReservation;

    public static void setReservation(Reservation reservation){
        currentReservation = reservation;
    }

    public static Reservation getReservation(){
        return currentReservation;
    }

    public static void clear(){
        currentReservation = null;
    }
}
