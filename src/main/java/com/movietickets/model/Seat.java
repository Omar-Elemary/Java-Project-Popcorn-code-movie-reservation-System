package com.movietickets.model;

public class Seat {
    private int seatID;
    private String row;
    private int num;
    private double price;
    private boolean status;             // AVAILABLE=True , RESERVED=False
    private transient Hall hall;        // Avoid circular JSON graph (Seat -> Hall -> Seats -> ...)

    public Seat() {
        this.status = true;
    }

    public Seat(int seatID, String row, int num, double price, Hall hall) {
        if (hall == null) System.out.println("Seat must belong to a Hall (Composition)");
        this.seatID = seatID;
        this.row = row;
        this.num = num;
        this.price = price;
        this.hall = hall;
        this.status = true;
    }
    public int getSeatID() {
        return seatID;
    }

    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }


    public void setPrice(double price) {
        this.price = price;
    }


    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }
    
    public boolean isAvailable() {
        return status;
    }
    
    public boolean reserve(Reservation reservation) {
        if (isAvailable() && reservation != null) {
            this.status = false;
            return true;
        }
        return false;
    }

    public void release() {
        this.status = true;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatID=" + seatID +
                ", row='" + row + '\'' +
                ", num=" + num +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Seat)) return false;
        Seat seat = (Seat) o;
        return seatID == seat.seatID;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(seatID);
    }
}

