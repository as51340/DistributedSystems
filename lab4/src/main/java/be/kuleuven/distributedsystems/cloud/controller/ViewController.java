package be.kuleuven.distributedsystems.cloud.controller;

import be.kuleuven.distributedsystems.cloud.Model;
import be.kuleuven.distributedsystems.cloud.entities.Quote;
import be.kuleuven.distributedsystems.cloud.entities.Seat;
import be.kuleuven.distributedsystems.cloud.entities.Show;
import be.kuleuven.distributedsystems.cloud.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class ViewController {
    private final Model model;

    @Autowired
    public ViewController(Model model) {
        this.model = model;
    }

    @GetMapping("/_ah/warmup")
    public void warmup() {
    }

    @GetMapping({"/", "/shows"})
    public ModelAndView viewShows(
            @CookieValue(value = "cart", required = false) String cartString) {
        List<Quote> quotes = Cart.fromCookie(cartString);
        ModelAndView modelAndView = new ModelAndView("shows");
        modelAndView.addObject("cartLength",
                Integer.toString(quotes.size()));
        modelAndView.addObject("manager", AuthController.getUser().isManager());
        modelAndView.addObject("shows", this.model.getShows());
        return modelAndView;
    }

    @GetMapping("/shows/{company}/{showId}")
    public ModelAndView viewShowTimes(
            @PathVariable String company,
            @PathVariable UUID showId,
            @CookieValue(value = "cart", required = false) String cartString) {
        List<Quote> quotes = Cart.fromCookie(cartString);
        ModelAndView modelAndView = new ModelAndView("show_times");
        modelAndView.addObject("cartLength",
                Integer.toString(quotes.size()));
        modelAndView.addObject("manager", AuthController.getUser().isManager());
        modelAndView.addObject("show",
                this.model.getShow(company, showId));
        modelAndView.addObject("showTimes",
                this.model.getShowTimes(company, showId)
                        .stream()
                        .sorted()
                        .collect(Collectors.toList()));
        return modelAndView;
    }

    @GetMapping("/shows/{company}/{showId}/{time}")
    public ModelAndView viewShowSeats(
            @PathVariable String company,
            @PathVariable UUID showId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime time,
            @CookieValue(value = "cart", required = false) String cartString) {
        List<Quote> quotes = Cart.fromCookie(cartString);
        ModelAndView modelAndView = new ModelAndView("show_seats");
        modelAndView.addObject("cartLength",
                Integer.toString(quotes.size()));
        modelAndView.addObject("manager", AuthController.getUser().isManager());
        modelAndView.addObject("show",
                this.model.getShow(company, showId));
        modelAndView.addObject("time",
                time.format(DateTimeFormatter.ofPattern("d MMM uuuu  H:mm")));
        modelAndView.addObject("seats",
                this.model.getAvailableSeats(company, showId, time)
                        .stream()
                        .filter(seat -> quotes.stream()
                                .noneMatch(quote -> quote.equals(new Quote(seat.getCompany(), seat.getShowId(), seat.getSeatId()))))
                        .sorted(Comparator.comparing(Seat::getType)
                                .thenComparing(seat -> seat.getName().substring(0, 1))
                                .thenComparing(seat -> Integer.parseInt(seat.getName().substring(1))))
                        .collect(Collectors.groupingBy(Seat::getType)));
        return modelAndView;
    }

    @GetMapping("/cart")
    public ModelAndView viewCart(
            @CookieValue(value = "cart", required = false) String cartString) {
        List<Quote> quotes = Cart.fromCookie(cartString);
        ModelAndView modelAndView = new ModelAndView("cart");
        modelAndView.addObject("cartLength",
                Integer.toString(quotes.size()));
        modelAndView.addObject("manager", AuthController.getUser().isManager());

        var shows = new HashMap<UUID, Show>();
        var seats = new HashMap<UUID, Seat>();
        for (var q : quotes) {
            if (!shows.containsKey(q.getShowId())) {
                shows.put(q.getShowId(), this.model.getShow(q.getCompany(), q.getShowId()));
            }
            if (!seats.containsKey(q.getSeatId())) {
                seats.put(q.getSeatId(), this.model.getSeat(q.getCompany(), q.getShowId(), q.getSeatId()));
            }
        }

        modelAndView.addObject("quotes", quotes);
        modelAndView.addObject("shows", shows);
        modelAndView.addObject("seats", seats);
        return modelAndView;
    }

    @GetMapping("/account")
    public ModelAndView viewAccount(
            @CookieValue(value = "cart", required = false) String cartString) throws Exception {
        List<Quote> quotes = Cart.fromCookie(cartString);
        ModelAndView modelAndView = new ModelAndView("account");
        modelAndView.addObject("cartLength",
                Integer.toString(quotes.size()));
        modelAndView.addObject("manager", AuthController.getUser().isManager());
        var bookings = this.model.getBookings(AuthController.getUser().getEmail());

        var shows = new HashMap<UUID, Show>();
        var seats = new HashMap<UUID, Seat>();
        for (var b : bookings) {
            for (var t : b.getTickets()) {
                if (!shows.containsKey(t.getShowId())) {
                    shows.put(t.getShowId(), this.model.getShow(t.getCompany(), t.getShowId()));
                }
                if (!seats.containsKey(t.getSeatId())) {
                    seats.put(t.getSeatId(), this.model.getSeat(t.getCompany(), t.getShowId(), t.getSeatId()));
                }
            }
        }

        modelAndView.addObject("bookings", bookings);
        modelAndView.addObject("seats", seats);
        modelAndView.addObject("shows", shows);
        return modelAndView;
    }

    @GetMapping("/manager")
    public ModelAndView viewManager(
            @CookieValue(value = "cart", required = false) String cartString) throws Exception {
        // TODO: limit this function to managers

        List<Quote> quotes = Cart.fromCookie(cartString);
        ModelAndView modelAndView = new ModelAndView("manager");
        modelAndView.addObject("cartLength",
                Integer.toString(quotes.size()));
        modelAndView.addObject("manager", AuthController.getUser().isManager());
        var bookings = this.model.getAllBookings();

        var shows = new HashMap<UUID, Show>();
        var seats = new HashMap<UUID, Seat>();
        for (var b : bookings) {
            for (var t : b.getTickets()) {
                if (!shows.containsKey(t.getShowId())) {
                    shows.put(t.getShowId(), this.model.getShow(t.getCompany(), t.getShowId()));
                }
                if (!seats.containsKey(t.getSeatId())) {
                    seats.put(t.getSeatId(), this.model.getSeat(t.getCompany(), t.getShowId(), t.getSeatId()));
                }
            }
        }

        modelAndView.addObject("bookings", bookings);
        modelAndView.addObject("seats", seats);
        modelAndView.addObject("shows", shows);
        modelAndView.addObject("bestCustomers", this.model.getBestCustomers());
        return modelAndView;
    }
}
