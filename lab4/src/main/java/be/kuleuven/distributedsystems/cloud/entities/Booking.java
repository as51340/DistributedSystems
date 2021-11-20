package be.kuleuven.distributedsystems.cloud.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * A collection of one or more tickets booked at the same time by one customer. Tickets within
 * a booking may belong to multiple shows across multiple theatre companies. A booking contains
 * references to the identifiers of its constituent tickets, and additionally has its own reference ID and
 * properties such as the customer who made it and a timestamp.
 */
public class Booking { // Collection of tickets
    private UUID id;
    private LocalDateTime time;
    private List<Ticket> tickets;
    private String customer;

    public Booking(UUID id, LocalDateTime time, List<Ticket> tickets, String customer) {
        this.id = id;
        this.time = time;
        this.tickets = tickets;
        this.customer = customer;
    }

    public UUID getId() {
        return this.id;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    public List<Ticket> getTickets() {
        return this.tickets;
    }

    public String getCustomer() {
        return this.customer;
    }
}
