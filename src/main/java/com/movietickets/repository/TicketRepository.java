package com.movietickets.repository;

import com.movietickets.model.Ticket;
import com.movietickets.model.User;
import com.movietickets.util.JsonHandler;
import java.util.List;
import java.util.Optional;

public class TicketRepository {
    private final JsonHandler<Ticket> jsonHandler;
    private static final String FILE_PATH = "data/tickets.json";

    public TicketRepository() {
        this.jsonHandler = new JsonHandler<>(Ticket.class, FILE_PATH);
    }

    public boolean add(Ticket ticket) {
        if (ticket == null) {
            return false;
        }
        return jsonHandler.add(ticket);
    }

    public boolean save(Ticket ticket) {
        return add(ticket);
    }

    public Optional<Ticket> getById(int ticketId) {
        return jsonHandler.findFirst(ticket -> ticket.getTicketID() == ticketId);
    }

    public Optional<Ticket> findById(int ticketId) {
        return getById(ticketId);
    }
    
    public List<Ticket> findByUser(User user) {
        if (user == null) {
            return List.of();
        }
        return jsonHandler.find(ticket -> 
            ticket.getReservationID() != null && 
            ticket.getReservationID().getUser() != null &&
            ticket.getReservationID().getUser().equals(user)
        );
    }
    
    public List<Ticket> findByUserId(int userId) {
        return jsonHandler.find(ticket -> 
            ticket.getReservationID() != null && 
            ticket.getReservationID().getUser() != null &&
            ticket.getReservationID().getUser().getId() == userId
        );
    }

    public List<Ticket> getAll() {
        return jsonHandler.readAll();
    }

    public List<Ticket> findAll() {
        return getAll();
    }

    public boolean update(Ticket ticket) {
        if (ticket == null) {
            return false;
        }
        return jsonHandler.update(ticket, t -> t.getTicketID() == ticket.getTicketID());
    }

    public boolean delete(int ticketId) {
        return jsonHandler.delete(ticket -> ticket.getTicketID() == ticketId);
    }

    public boolean deleteById(int ticketId) {
        return delete(ticketId);
    }

    public boolean delete(Ticket ticket) {
        if (ticket == null) {
            return false;
        }
        return delete(ticket.getTicketID());
    }

    public boolean exists(int ticketId) {
        return getById(ticketId).isPresent();
    }

    public boolean existsById(int ticketId) {
        return exists(ticketId);
    }

    public int count() {
        return jsonHandler.size();
    }
}