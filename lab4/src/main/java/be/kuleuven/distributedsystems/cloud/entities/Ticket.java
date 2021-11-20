package be.kuleuven.distributedsystems.cloud.entities;

import java.util.UUID;

/**
 * A final allocation of one seat to one specific customer. Not every seat has a corresponding ticket:
 * if no ticket is present, the seat remains available. A ticket has a unique identifier. A ticket can be
 * deleted if the customer cancels it.
 */
public class Ticket {
    private String company;
    private UUID showId;
    private UUID seatId; // Ticket ima reference na seat
    private UUID ticketId;
    private String customer;

    public Ticket() {
    }

    public Ticket(String company, UUID showId, UUID seatId, UUID ticketId, String customer) {
        this.company = company;
        this.showId = showId;
        this.seatId = seatId;
        this.ticketId = ticketId;
        this.customer = customer;
    }

    public String getCompany() {
        return company;
    }

    public UUID getShowId() {
        return showId;
    }

    public UUID getSeatId() {
        return this.seatId;
    }

    public UUID getTicketId() {
        return this.ticketId;
    }

    public String getCustomer() {
        return this.customer;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Ticket)) {
            return false;
        }
        var other = (Ticket) o;
        return this.ticketId.equals(other.ticketId)
                && this.seatId.equals(other.seatId)
                && this.showId.equals(other.showId)
                && this.company.equals(other.company);
    }

    @Override
    public int hashCode() {
        return this.company.hashCode() * this.showId.hashCode() * this.seatId.hashCode() * this.ticketId.hashCode();
    }
}
