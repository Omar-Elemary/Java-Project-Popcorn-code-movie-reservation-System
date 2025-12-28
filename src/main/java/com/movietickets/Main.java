package com.movietickets;

import java.util.ArrayList;
import java.util.List;

import com.movietickets.model.Admin;
import com.movietickets.model.Cinema;
import com.movietickets.model.Hall;
import com.movietickets.model.Movie;
import com.movietickets.model.Payment;
import com.movietickets.model.Reservation;
import com.movietickets.model.Seat;
import com.movietickets.model.Show;
import com.movietickets.model.Ticket;
import com.movietickets.model.User;
import com.movietickets.repository.HallRepository;
import com.movietickets.repository.MovieRepository;
import com.movietickets.repository.PaymentRepository;
import com.movietickets.repository.ReservationRepository;
import com.movietickets.repository.SeatRepository;
import com.movietickets.repository.ShowRepository;
import com.movietickets.repository.TicketRepository;
import com.movietickets.repository.UserRepository;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Movie Ticket System!");
        System.out.println("=====================================");
        
        // Create repositories
        UserRepository userRepo = new UserRepository();
        MovieRepository movieRepo = new MovieRepository();
        ShowRepository showRepo = new ShowRepository();
        HallRepository hallRepo = new HallRepository();
        SeatRepository seatRepo = new SeatRepository();
        
        // Create a cinema
        Cinema cinema = new Cinema("CineMax", "123 Main Street, City");
        
        // Create a hall
        Hall hall = new Hall(1, 100, cinema);
        hallRepo.add(hall);
        cinema.addHall(hall);
        
        // Create seats using simple loops
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                int seatId = i * 10 + j;
                String row = String.valueOf((char)('A' + i - 1));
                Seat seat = new Seat(seatId, row, j, 15.0, hall);
                hall.addSeat(seat);
                seatRepo.save(seat);
            }
        }
        
        // Create a movie
        Movie movie = new Movie(1, "The Great Adventure", "An epic adventure story", 120, "Action");
        movieRepo.add(movie);
        
        // Create a show
        Show show = new Show(1, "19:00", "2024-01-15", movie, hall);
        showRepo.add(show);
        
        // Create a user
        User user = new User("john.doe@email.com");
        userRepo.add(user);
        
        // Create an admin
        Admin admin = new Admin("admin@cinema.com");
        
        // Use admin method to add movie
        System.out.println("\nAdmin Operations:");
        admin.addMovie(movie, movieRepo);
        
        // Create another movie
        Movie movie2 = new Movie(2, "Comedy Night", "A hilarious comedy", 90, "Comedy");
        movieRepo.add(movie2);
        
        // Create another show
        Show show2 = new Show(2, "21:00", "2024-01-15", movie2, hall);
        showRepo.add(show2);
        
        // Use user method to book ticket
        System.out.println("\nUser Operations:");
        user.bookTicket();
        
        // Create a reservation
        ReservationRepository reservationRepo = new ReservationRepository();
        List<Seat> hallSeats = hall.getSeats();
        List<Integer> selectedSeatIds = new ArrayList<>();
        
        // Select first 2 seats using simple loop
        int seatsToBook = 2;
        if (hallSeats.size() >= seatsToBook) {
            for (int i = 0; i < seatsToBook; i++) {
                Seat seat = hallSeats.get(i);
                selectedSeatIds.add(seat.getSeatID());
            }
        }
        
        Reservation reservation = new Reservation(user, show, selectedSeatIds);
        reservationRepo.add(reservation);
        
        // Use show method to reserve seats
        if (show.reserveSeats(reservation, selectedSeatIds)) {
            System.out.println("Seats reserved successfully!");
            System.out.println("Reservation ID: " + reservation.getReservationId());
            // Use reservation method to get total price
            System.out.println("Total Price: $" + reservation.getTotalPrice());
            
            // Print seat labels using simple loop
            String seatsLabel = "";
            for (int i = 0; i < selectedSeatIds.size(); i++) {
                int seatId = selectedSeatIds.get(i);
                Seat seat = seatRepo.findById(seatId).orElse(null);
                if (seat != null) {
                    if (i > 0) {
                        seatsLabel += ", ";
                    }
                    seatsLabel += seat.getRow() + seat.getNum();
                }
            }
            System.out.println("Seats: " + seatsLabel);
        }
        
        // Create a payment
        PaymentRepository paymentRepo = new PaymentRepository();
        Payment payment = new Payment(1, reservation.getReservationId(), 
                                    reservation.getTotalPrice(), "card", 0, "");
        paymentRepo.add(payment);

        // Process payment using payment method
        System.out.println("\nPayment Processing:");
        if (payment.processPayment()) {
            // Use reservation method to complete
            reservation.complete();
            System.out.println("Payment completed successfully!");
        }
        
        // Generate ticket
        TicketRepository ticketRepo = new TicketRepository();
        int firstSeatId = selectedSeatIds.get(0);
        Ticket ticket = new Ticket(1, reservation.getTotalPrice(), 
                                 reservation, show, firstSeatId, hall.getHallID());
        ticketRepo.add(ticket);
        
        // Use ticket method to generate PDF
        System.out.println("\nGenerating PDF Ticket:");
        ticket.printToPDF();
        
        // Display system information
        System.out.println("\nSystem Information:");
        System.out.println("Cinema: " + cinema.getName() + " (" + cinema.getAddress() + ")");
        System.out.println("Movies: " + movieRepo.getAll().size());
        System.out.println("Users: " + userRepo.getAll().size());
        System.out.println("Shows: " + showRepo.getAll().size());
        System.out.println("Halls: " + hallRepo.getAll().size());
        System.out.println("Reservations: " + reservationRepo.getAll().size());
        System.out.println("Payments: " + paymentRepo.getAll().size());
        System.out.println("Tickets: " + ticketRepo.getAll().size());
        
        // Use admin method to generate report
        System.out.println("\nAdmin Report:");
        System.out.println(admin.generateReport(show));
        
        System.out.println("\nDemo completed successfully!");
    }
}
