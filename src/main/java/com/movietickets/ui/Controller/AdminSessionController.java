package com.movietickets.ui.Controller;

import com.movietickets.model.Admin;

public class AdminSessionController {
    private static Admin currentAdmin;

    public static void setAdmin(Admin admin){
        currentAdmin = admin;
    }

    public static Admin getAdmin(){
        return currentAdmin;
    }

    public static void clear(){
        currentAdmin = null;
    }
}
