package be.kuleuven.distributedsystems.cloud;

import be.kuleuven.distributedsystems.cloud.entities.*;
import be.kuleuven.distributedsystems.cloud.pubsub.PubSubHandler;
import be.kuleuven.distributedsystems.cloud.services.ReliableTheatresService;
import com.google.pubsub.v1.Encoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class Model {

    private ReliableTheatresService reliableTheatresService = null;

    public Model(ReliableTheatresService reliableTheatresService, PubSubHandler pubSubHandler) {
        this.reliableTheatresService = reliableTheatresService;
    }

    public List<Show> getShows() {
        return this.reliableTheatresService.getShows();
    }

    public Show getShow(String company, UUID showId) {
        return this.reliableTheatresService.getShow(company, showId);
    }

    public List<LocalDateTime> getShowTimes(String company, UUID showId) {
        return this.reliableTheatresService.getShowTimes(company, showId);
    }

    public List<Seat> getAvailableSeats(String company, UUID showId, LocalDateTime time) {
        return this.reliableTheatresService.getAvailableSeats(company, showId, time);
    }

    public Seat getSeat(String company, UUID showId, UUID seatId) {
        return this.reliableTheatresService.getSeat(company, showId,seatId);
    }

    public Ticket getTicket(String company, UUID showId, UUID seatId) {
        return this.reliableTheatresService.getTicket(company, showId,seatId);
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
        // Put method on customer, ticket or something to reliable theatre service
    }
}
