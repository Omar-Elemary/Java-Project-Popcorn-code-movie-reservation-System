package com.movietickets.ui.Controller;

import com.movietickets.model.Show;

public class ShowSessionController {
    private static Show currentShow;

    public static void setShow(Show show){
        currentShow = show;
    }

    public static Show getShow(){
        return currentShow;
    }

    public static void clear(){
        currentShow = null;
    }
}
