package be.kuleuven.distributedsystems.cloud.entities;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A specific instance within a show, characterised by (at least) a concrete time and seat number.
 * Additionally, a seat has properties such as a type (e.g., floor/balcony) and price. A seat can only be
 * booked by one customer at a time, i.e., belong to exactly one ticket, or zero tickets if the seat has not
 * yet been booked
 */
public class Seat {
    private String company;
    private UUID showId;
    private UUID seatId;
    private LocalDateTime time;
    private String type;
    private String name;
    private double price;

    public Seat() {
    }

    public Seat(String company, UUID showId, UUID seatId, LocalDateTime time, String type, String name, double price) {
        this.company = company;
        this.showId = showId;
        this.seatId = seatId;
        this.time = time;
        this.type = type;
        this.name = name;
        this.price = price;
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

    public LocalDateTime getTime() {
        return this.time;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Seat)) {
            return false;
        }
        var other = (Seat) o;
        return this.company.equals(other.company)
                && this.showId.equals(other.showId)
                && this.seatId.equals(other.seatId);
    }

    @Override
    public int hashCode() {
        return this.company.hashCode() * this.showId.hashCode() * this.seatId.hashCode();
    }
}
