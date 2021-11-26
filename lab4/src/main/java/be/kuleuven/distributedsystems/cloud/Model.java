package be.kuleuven.distributedsystems.cloud;

import be.kuleuven.distributedsystems.cloud.entities.*;
import be.kuleuven.distributedsystems.cloud.pubsub.PubSubHandler;
import be.kuleuven.distributedsystems.cloud.services.TheatreService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class Model {

    private TheatreService theatreService = null;

    private Map<String, List<Booking>> customerBookings = new HashMap<>();

    // TODO Do we need pubsubhandler here?
    public Model(TheatreService theatreService, PubSubHandler pubSubHandler) {
        this.theatreService = theatreService;
    }

    public List<Show> getShows() {
        return this.theatreService.getShows();
    }

    public Show getShow(String company, UUID showId) {
        return this.theatreService.getShow(company, showId);
    }

    public List<LocalDateTime> getShowTimes(String company, UUID showId) {
        return this.theatreService.getShowTimes(company, showId);
    }

    public List<Seat> getAvailableSeats(String company, UUID showId, LocalDateTime time) {
        System.out.println("time=" + time.toString());
        return this.theatreService.getAvailableSeats(company, showId, time);
    }

    public Seat getSeat(String company, UUID showId, UUID seatId) {
        return this.theatreService.getSeat(company, showId,seatId);
    }

    public Ticket getTicket(String company, UUID showId, UUID seatId) {
        return this.theatreService.getTicket(company, showId,seatId);
    }

    /**
     * I don't think here are many bookings per one customer
     * @param customer
     * @return
     */
    public List<Booking> getBookings(String customer) {
        var bookings = this.customerBookings.get(customer);
        if(bookings == null) {
            return new ArrayList<>();
        }
        return bookings;
    }

    /**
     * I would say this is quite expensive
     * @return
     */
    public List<Booking> getAllBookings() {
        List<Booking> allBookings = new ArrayList<>();
        for(String customer: this.customerBookings.keySet()) {
            allBookings.addAll(this.customerBookings.get(customer));
        }
        return allBookings;
    }

    /**
     *
     * @return
     */
    public Set<String> getBestCustomers() {
        Set<String> bestCustomers = new HashSet<>();
        int maxTickets = 0;
        // Find max num of tickets
        for(String customer: this.customerBookings.keySet()) {
            List<Booking> currBookings = this.customerBookings.get(customer);
            int currentTickets = 0;
            for(Booking booking: currBookings) {
                currentTickets += booking.getTickets().size();
            }
            if(currentTickets > maxTickets) {
                maxTickets = currentTickets;
            }
        }
        for(String customer: this.customerBookings.keySet()) {
            int currentTickets = 0;
            List<Booking> currBookings = this.customerBookings.get(customer);
            for(Booking booking: currBookings) {
                currentTickets += booking.getTickets().size();
            }
            if(currentTickets == maxTickets) {
                bestCustomers.add(customer);
            }
        }
        return bestCustomers;
    }

    /**
     * Maybe this
     * @param quotes
     * @param customer
     */
    public void confirmQuotes(List<Quote> quotes, String customer) {
        // TODO: reserve all seats for the given quotes

        boolean res = this.theatreService.reserveSeat(quotes.get(0), customer);
        System.out.println("Result from confirming quote: " + res);
    }

}
