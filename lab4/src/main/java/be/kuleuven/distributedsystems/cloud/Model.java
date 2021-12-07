package be.kuleuven.distributedsystems.cloud;

import be.kuleuven.distributedsystems.cloud.entities.*;
import be.kuleuven.distributedsystems.cloud.pubsub.PubSubHandler;
import be.kuleuven.distributedsystems.cloud.services.SendGridEmail;
import be.kuleuven.distributedsystems.cloud.services.ServiceException;
import be.kuleuven.distributedsystems.cloud.services.TheatreService;
import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class Model {

    // Theatre service for fetching stuff
    private TheatreService theatreService = null;

    // Currently save bookings in memory
    private Map<String, List<Booking>> customerBookings = new HashMap<>();

    // SendGrid setup
    private SendGridEmail sendGrid;

    // Number of times that we will try to contact theatre companies
    private static final int repeat = 5;

    public Model(TheatreService theatreService, SendGridEmail sendGrid) {
        this.theatreService = theatreService;
        this.sendGrid = sendGrid;
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
                // System.err.println("Trying to fetch show: " + showId.toString());
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
                // System.err.println("Times: " + times);
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
                // System.err.println("Seats: " + seats);
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
                // System.out.println(ex.getMessage());
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
                // System.out.println(ex.getMessage());
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
                    Ticket currentTicket = this.theatreService.putTicket(quote, customer);
                    reserved = true;
                    tickets.add(currentTicket);
                    break;
                } catch(ServiceException ex) {
                    System.err.println("Seat cannot be reserved: " + quote.getSeatId());
                }
            }
            if(reserved) {
                cnt += 1;
            } else {
                break;
            }
        }
        if(cnt == quotes.size()) {
            System.out.println("All seats successfully reserved, sending mail to customer and adding booking to memory...");
            Booking booking = new Booking(UUID.randomUUID(), LocalDateTime.now(), tickets, customer);
            this.customerBookings.computeIfAbsent(customer, k -> new ArrayList<>());
            this.customerBookings.get(customer).add(booking);
            // TODO try to add HTML to mail, return to him list of generated tickets
            sendGrid.sendEmail("Ticket reservation success", "Dear customer, all your tickets have been successfully confirmed...", customer);
        } else {
            System.err.println("Error happened while reserving seats, deleting old tickets. Cnt: " + cnt);
            for(Ticket ticket: tickets) {
                boolean deleted = false;
                for(int i = 0; i < repeat; i++) {
                    try {
                        this.theatreService.deleteTicket(ticket);
                        System.out.println("Deleted ticket: " + ticket.getTicketId().toString());
                        deleted = true;
                        break;
                    } catch(ServiceException | NullPointerException ex) {

                    }
                }
                if(!deleted) {
                    System.out.println("Failed to delete old ticket, further action needed...");
                }
            }
            // TODO try to add HTML to mail
            sendGrid.sendEmail("Ticket reservation failure",
                    "Dear customer, your tickets couldn't be processed, please try to do again or feel free to contact us for further questions...",
                    customer);
        }
    }


    private String getSuccessfulMailContent(List<Ticket> tickets, String customer) {
        StringBuilder seatsStr = new StringBuilder("<ol>");
        for(Ticket ticket: tickets) {
            seatsStr.append("<li>").append(ticket.getSeatId()).append("</li>\n");
        }
        seatsStr.append("</ol>\n");

        StringBuilder finalStr = new StringBuilder();
        finalStr.append("<html>\nDear ").append(customer).append(", \nYour request for seats\n").append(seatsStr.toString());
        return null;
    }
}
