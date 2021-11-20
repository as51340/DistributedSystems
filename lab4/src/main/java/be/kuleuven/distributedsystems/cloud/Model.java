package be.kuleuven.distributedsystems.cloud;

import be.kuleuven.distributedsystems.cloud.entities.*;
import be.kuleuven.distributedsystems.cloud.services.ReliableTheatresService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class Model {

    private final String reliableTheatresURL = "https://reliabletheatrecompany.com/shows?key=wCIoTqec6vGJijW2meeqSokanZuqOL";

    private ReliableTheatresService reliableTheatresService = null;

    public Model(ReliableTheatresService reliableTheatresService) {
        this.reliableTheatresService = reliableTheatresService;
    }

    public List<Show> getShows() {
        // TODO: return all shows
        //return new ArrayList<>();
        return this.reliableTheatresService.getShows();
    }

    public Show getShow(String company, UUID showId) {
        // TODO: return the given show
        return null;
    }

    public List<LocalDateTime> getShowTimes(String company, UUID showId) {
        // TODO: return a list with all possible times for the given show
        return new ArrayList<>();
    }

    public List<Seat> getAvailableSeats(String company, UUID showId, LocalDateTime time) {
        // TODO: return all available seats for a given show and time
        return new ArrayList<>();
    }

    public Seat getSeat(String company, UUID showId, UUID seatId) {
        // TODO: return the given seat
        return null;
    }

    public Ticket getTicket(String company, UUID showId, UUID seatId) {
        // TODO: return the ticket for the given seat
        return null;
    }

    /**
     * I don't think here are many bookings per one customer
     * @param customer
     * @return
     */
    public List<Booking> getBookings(String customer) {
        // TODO: return all bookings from the customer
        return new ArrayList<>();
    }

    /**
     * I would say this is quite expensive
     * @return
     */
    public List<Booking> getAllBookings() {
        // TODO: return all bookings
        return new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public Set<String> getBestCustomers() {
        // TODO: return the best customer (highest number of tickets, return all of them if multiple customers have an equal amount)
        return null;
    }

    /**
     * Maybe this
     * @param quotes
     * @param customer
     */
    public void confirmQuotes(List<Quote> quotes, String customer) {
        // TODO: reserve all seats for the given quotes
    }
}
