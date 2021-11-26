package be.kuleuven.distributedsystems.cloud;

import be.kuleuven.distributedsystems.cloud.entities.*;
import be.kuleuven.distributedsystems.cloud.pubsub.PubSubHandler;
import be.kuleuven.distributedsystems.cloud.services.ServiceException;
import be.kuleuven.distributedsystems.cloud.services.TheatreService;
import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class Model {

    private TheatreService theatreService = null;

    private Map<String, List<Booking>> customerBookings = new HashMap<>();

    private static final int repeat = 5;

    // TODO Do we need pubsubhandler here?
    public Model(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    public List<Show> getShows() {
        List<Show> shows = null;
        for(int i = 0; i < repeat; i++) {
            try {
               shows = this.theatreService.getShows();
               break;
            } catch(ServiceException ex) {
                System.err.println("Shows: " + shows);
            }
        }
        if(shows == null) {
            return new ArrayList<>();
        }
        return shows;
    }

    public Show getShow(String company, UUID showId) {
        Show show = null;
        for(int i = 0; i < repeat; i++) {
            try {
                show = this.theatreService.getShow(company, showId);
            } catch(ServiceException ex) {
                System.err.println("Trying to fetch show: " + showId.toString());
            }
        }
        return show;
    }

    public List<LocalDateTime> getShowTimes(String company, UUID showId) {
        List<LocalDateTime> times = null;
        for(int i = 0; i < repeat; i++) {
            try {
                times = this.theatreService.getShowTimes(company, showId);
                break;
            } catch(ServiceException ex) {
                // just continue
                System.err.println("Times: " + times);
            }
        }
        if(times == null) {
            return new ArrayList<>();
        }
        return times;
    }

    public List<Seat> getAvailableSeats(String company, UUID showId, LocalDateTime time) {
        List<Seat> seats = null;
        for(int i = 0; i < repeat; i++) {
            try {
                seats = this.theatreService.getAvailableSeats(company, showId, time);
                break;
            } catch(ServiceException ex) {
                System.err.println("Seats: " + seats);
            }
        }
        if(seats == null) {
            return new ArrayList<>();
        }
        return seats;
    }

    public Seat getSeat(String company, UUID showId, UUID seatId) {
       Seat seat = null;
        for(int i = 0; i < repeat; i++) {
            try {
                seat = this.theatreService.getSeat(company, showId, seatId);
                break;
            } catch(ServiceException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return seat;
    }

    public Ticket getTicket(String company, UUID showId, UUID seatId) {
        Ticket ticket = null;
        for(int i = 0; i < repeat; i++) {
            try {
                ticket = this.theatreService.getTicket(company, showId, seatId);
                break;
            } catch(ServiceException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return ticket;
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
        // Create a booking.
        // There is possibility that we reserved one ticket but cannot reserve some other ticket even after 5 repeats.
        int cnt = 0;
        List<Ticket> tickets = new ArrayList<>();
        for(Quote quote: quotes) {
            boolean reserved = false;
            for(int i = 0; i < repeat; i++) {
                try {
                    Seat currSeat = this.theatreService.reserveSeat(quote, customer);

                    reserved = true;
                    Ticket currentTicket = new Ticket(quote.getCompany(), quote.getShowId(), quote.getSeatId(), UUID.randomUUID(), customer);
                    System.out.println("New ticket: " + currentTicket.getTicketId().toString());
                    System.out.println("New seat: " + quote.getSeatId().toString());
                    tickets.add(currentTicket);
                    break;
                } catch(ServiceException ex) {
                    System.err.println("Seat cannot be reserved: " + quote.getSeatId());
                }
            }
            if(reserved) {
                cnt += 1;
            } else {
                // Do not continue
                break;
            }
        }
        if(cnt == quotes.size()) {
            System.out.println("All seats successfully reserved!");
            Booking booking = new Booking(UUID.randomUUID(), LocalDateTime.now(), tickets, customer);
            if(this.customerBookings.get(customer) == null) {
                this.customerBookings.put(customer, new ArrayList<>());
            }
            this.customerBookings.get(customer).add(booking);
        } else {
            System.err.println("Error happened while reserving seats.");
        }
    }

}
