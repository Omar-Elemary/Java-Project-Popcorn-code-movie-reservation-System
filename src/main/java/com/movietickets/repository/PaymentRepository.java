package com.movietickets.repository;

import com.movietickets.model.Payment;
import com.movietickets.util.JsonHandler;
import java.util.List;
import java.util.Optional;

public class PaymentRepository {
    private final JsonHandler<Payment> jsonHandler;
    private static final String FILE_PATH = "data/payments.json";

    public PaymentRepository() {
        this.jsonHandler = new JsonHandler<>(Payment.class, FILE_PATH);
    }

    public boolean add(Payment payment) {
        if (payment == null) {
            return false;
        }
        return jsonHandler.add(payment);
    }

    public boolean save(Payment payment) {
        return add(payment);
    }

    public Optional<Payment> getById(int paymentId) {
        return jsonHandler.findFirst(payment -> payment.getPaymentID() == paymentId);
    }

    public Optional<Payment> findById(int paymentId) {
        return getById(paymentId);
    }
    
    public List<Payment> findByReservationId(int reservationId) {
        return jsonHandler.find(payment -> payment.getReservationID() == reservationId);
    }
    
    public List<Payment> findByStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            return List.of();
        }
        return jsonHandler.find(payment -> 
            payment.getStatus() != null && 
            payment.getStatus().equalsIgnoreCase(status.trim())
        );
    }

    public List<Payment> findByMethod(String method) {
        if (method == null || method.trim().isEmpty()) {
            return List.of();
        }
        return jsonHandler.find(payment -> 
            payment.getMethod() != null && 
            payment.getMethod().equalsIgnoreCase(method.trim())
        );
    }

    public List<Payment> getAll() {
        return jsonHandler.readAll();
    }

    public List<Payment> findAll() {
        return getAll();
    }

    public boolean update(Payment payment) {
        if (payment == null) {
            return false;
        }
        return jsonHandler.update(payment, p -> p.getPaymentID() == payment.getPaymentID());
    }

    public boolean delete(int paymentId) {
        return jsonHandler.delete(payment -> payment.getPaymentID() == paymentId);
    }

    public boolean deleteById(int paymentId) {
        return delete(paymentId);
    }

    public boolean delete(Payment payment) {
        if (payment == null) {
            return false;
        }
        return delete(payment.getPaymentID());
    }

    public boolean exists(int paymentId) {
        return getById(paymentId).isPresent();
    }

    public boolean existsById(int paymentId) {
        return exists(paymentId);
    }

    public int count() {
        return jsonHandler.size();
    }
}