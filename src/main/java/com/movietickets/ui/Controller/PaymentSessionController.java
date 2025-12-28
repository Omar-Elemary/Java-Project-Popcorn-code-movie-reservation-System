package com.movietickets.ui.Controller;

import com.movietickets.model.Payment;

public class PaymentSessionController {
    private static Payment currentPayment;

    public static void setPayment(Payment payment){
        currentPayment = payment;
    }

    public static Payment getPayment(){
        return currentPayment;
    }

    public static void clear(){
        currentPayment = null;
    }
}
