package com.movietickets.model;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.UUID;

public class Payment {
    private int paymentID;
    private int reservationID;
    private double amount;
    private String method;    // e.g., "card", "cash", "paypal"
    private String status;    // e.g., "pending", "completed", "failed"
    private int transactionID;
    private String time;

    private static final String API_URL = "https://reqres.in/api/payments";

    public Payment() {}

    public Payment(int paymentID, int reservationID, double amount, String method, int transactionID, String time) {
        this.paymentID = paymentID;
        this.reservationID = reservationID;
        this.amount = amount;
        this.method = method;
        this.transactionID = transactionID;
        this.time = time;
    }

    /**
     * Simulates sending a payment request to an external API.
     *
     * @return true if payment succeeds, false otherwise
     */
    /*
    API Connected to server
    public boolean processPayment() {
        System.out.println("Connecting to payment API...");

        // Create a fake transaction ID locally
        this.transactionID = UUID.randomUUID().hashCode();

        // Create JSON body manually
        String jsonBody = String.format(
                "{\"reservation_id\": %d, \"amount\": %.2f, \"method\": \"%s\"}",
                reservationID, amount, method
        );

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200 || response.statusCode() == 201) {
                this.status = "COMPLETED";
                this.time = String.valueOf(System.currentTimeMillis());
                System.out.println("Payment processed successfully via API!");
                System.out.println("Response: " + response.body());
                return true;
            } else {
                this.status = "FAILED";
                System.out.println("Payment API failed with status " + response.statusCode());
                return false;
            }

        } catch (IOException | InterruptedException e) {
            this.status = "FAILED";
            System.out.println("Payment API request error: " + e.getMessage());
            return false;
        }
    }


    public boolean refundPayment() {
        System.out.println("Sending refund request for transaction: " + transactionID);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + "/" + transactionID))
                .DELETE()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200 || response.statusCode() == 204) {
                this.status = "REFUNDED";
                System.out.println("Payment refunded successfully!");
                return true;
            } else {
                System.out.println("Refund failed with status: " + response.statusCode());
                return false;
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Refund API request error: " + e.getMessage());
            return false;
        }
    }

    */
    public boolean processPayment() {
        System.out.println("Processing payment offline...");

        // Fake transaction ID
        this.transactionID = UUID.randomUUID().toString().hashCode();

        // Simulate processing delay
        try {
            Thread.sleep(500); // 0.5 second (optional)
        } catch (InterruptedException ignored) {}

        // Mark as completed
        this.status = "COMPLETED";
        this.time = String.valueOf(System.currentTimeMillis());

        System.out.println("Payment processed successfully (offline simulation).");
        System.out.println("Transaction ID: " + transactionID);

        return true;
    }

    public boolean refundPayment() {
        System.out.println("Processing refund offline for transaction: " + transactionID);

        // Fake delay
        try {
            Thread.sleep(300);
        } catch (InterruptedException ignored) {}

        this.status = "REFUNDED";

        System.out.println("Refund completed successfully (offline simulation).");
        return true;
    }
    // Getters and setters
    public int getPaymentID() { return paymentID; }
    public void setPaymentID(int paymentID) { this.paymentID = paymentID; }
    public int getReservationID() { return reservationID; }
    public void setReservationID(int reservationID) { this.reservationID = reservationID; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getTransactionID() { return transactionID; }
    public void setTransactionID(int transactionID) { this.transactionID = transactionID; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return paymentID == payment.paymentID;
    }

    @Override
    public int hashCode() { return Objects.hash(paymentID); }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentID=" + paymentID +
                ", reservationID=" + reservationID +
                ", amount=" + amount +
                ", method='" + method + '\'' +
                ", status='" + status + '\'' +
                ", transactionID=" + transactionID +
                '}';
    }
}
