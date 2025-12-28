package com.movietickets.ui.Controller;

import com.movietickets.model.User;

public class UserSessionController {
    private static User currentUser;

    public static void setUser(User user){
        currentUser = user;
    }

    public static User getUser(){
        return currentUser;
    }

    public static void clear(){
        currentUser = null;
    }
}