package be.kuleuven.distributedsystems.cloud;

import be.kuleuven.distributedsystems.cloud.entities.*;
import be.kuleuven.distributedsystems.cloud.services.ServiceException;
import be.kuleuven.distributedsystems.cloud.services.TheatreService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Component
public class Model {

  private final TheatreService theatreService;
  private final Firestore db;
  private final InternalShows internalShows;
  private final Map<String, List<Booking>> customerBookings = new HashMap<>();
  private static final int repeat = 5;

  public Model(TheatreService theatreService) {
    this.theatreService = theatreService;

    Database database = new Database();
    this.db = database.initDB();

    internalShows = new InternalShows();
    internalShows.initInternalShows(db);
  }

  public List<Show> getShows() {
    List<Show> shows = new ArrayList<>();
    for (int i = 0; i < repeat; i++) {
      try {
        shows.addAll(this.theatreService.getShows());
        break;
      } catch (ServiceException ex) {
        ex.printStackTrace();
      }
    }
    //Add internal shows as well
    shows.addAll(internalShows.dbJsonToPojo(db));
    return shows;
  }

  public Show getShow(String company, UUID showId) {
    Show show = null;
    if(!company.equals("internal")) {
      for (int i = 0; i < repeat; i++) {
        try {
          show = this.theatreService.getShow(company, showId);
        } catch (ServiceException ex) {
          System.err.println("Trying to fetch show: " + showId.toString());
        }
      }
    } else {
      show = internalShows.getShow(showId, db);
    }
    return show;
  }

  public List<LocalDateTime> getShowTimes(String company, UUID showId) {
    List<LocalDateTime> times = null;
    if(!company.equals("internal")) {
      for (int i = 0; i < repeat; i++) {
        try {
          times = this.theatreService.getShowTimes(company, showId);
          break;
        } catch (ServiceException ex) {
          ex.printStackTrace();
        }
      }
    } else {
      times = internalShows.getShowTimes(showId, db);
    }
    if (times == null) {
      return new ArrayList<>();
    }
    return times;
  }

  public List<Seat> getAvailableSeats(String company, UUID showId, LocalDateTime time) {
    List<Seat> seats = null;
    if(!company.equals("internal")) {
      for (int i = 0; i < repeat; i++) {
        try {
          seats = this.theatreService.getAvailableSeats(company, showId, time);
          break;
        } catch (ServiceException ex) {
          ex.printStackTrace();
        }
      }
    } else {
      seats = internalShows.getAvailableSeats(showId, time);
    }
    if (seats == null) {
      return new ArrayList<>();
    }
    return seats;
  }

  public Seat getSeat(String company, UUID showId, UUID seatId) {
    Seat seat = null;
    if(!company.equals("internal")) {
      for (int i = 0; i < repeat; i++) {
        try {
          seat = this.theatreService.getSeat(company, showId, seatId);
          break;
        } catch (ServiceException ex) {
          System.out.println(ex.getMessage());
        }
      }
    } else {
      seat = internalShows.getSeat(showId, seatId, db);
    }
    return seat;
  }

  @SuppressWarnings("unchecked")
  public List<Booking> getBookings(String customer) throws ExecutionException, InterruptedException {
    List<Booking> bookings = new ArrayList<>();
    ApiFuture<QuerySnapshot> query = db.collection("ds").document(customer).collection("bookings").get();
    QuerySnapshot querySnapshot = query.get();
    List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
    for (QueryDocumentSnapshot document : documents) {
      // Get all tickets that are saved in the booking and create a list from them
      Map<String, Object> map = document.getData();
      List<Ticket> ticketList = new ArrayList<>();
      for (Map.Entry<String, Object> entry : map.entrySet())
        if(entry.getKey().equals("tickets")) {
          List<Map<String, Object>> ticketListDb = (List<Map<String, Object>>) entry.getValue();
          for (Map<String, Object> l : ticketListDb) {
            UUID showID = UUID.fromString((String) l.get("showID"));
            UUID seatID = UUID.fromString((String) l.get("seatID"));
            UUID ticketID = UUID.fromString((String) l.get("ticketID"));
            String company = (String) l.get("company");
            Ticket t = new Ticket(company, showID, seatID, ticketID, customer);
            System.out.println("showID:" + t.getShowId() + "\nseatID:" + t.getSeatId()
                              + "\nticketID:" + t.getTicketId() + "\ncompany:" + t.getCompany());
            ticketList.add(t);
          }
        }
      // Extract all other booking data
      UUID uuid = UUID.fromString(Objects.requireNonNull(document.getString("UUID")));
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSSSSSSSS");
      LocalDateTime time = LocalDateTime.parse(Objects.requireNonNull(document.getString("time")), formatter);

      bookings.add(new Booking(uuid, time, ticketList, customer));
    }
    // ---------------------------------------------------------------//
//    var bookings = this.customerBookings.get(customer);
//    if (bookings == null) {
//      return new ArrayList<>();
//    }
    return bookings;
  }

  public List<Booking> getAllBookings() throws ExecutionException, InterruptedException {
    List<Booking> allBookings = new ArrayList<>();
    ApiFuture<QuerySnapshot> future = db.collection("ds").get();
    List<QueryDocumentSnapshot> documents = future.get().getDocuments();
    for (QueryDocumentSnapshot document : documents)
      allBookings.addAll(getBookings(document.getId()));
    return allBookings;
  }

  public Set<String> getBestCustomers() throws ExecutionException, InterruptedException {
    Set<String> bestCustomers = new HashSet<>();
    int maxTickets = 0;
    List<Booking> costumerBookings;
    ApiFuture<QuerySnapshot> future = db.collection("ds").get();
    List<QueryDocumentSnapshot> documents = future.get().getDocuments();

    for (QueryDocumentSnapshot document : documents) {
      costumerBookings = getBookings(document.getId());
      int currentTickets = 0;
      for (Booking booking : costumerBookings)
        currentTickets += booking.getTickets().size();
      if (currentTickets > maxTickets)
        maxTickets = currentTickets;
    }

    for (QueryDocumentSnapshot document : documents) {
      costumerBookings = getBookings(document.getId());
      int currentTickets = 0;
      for (Booking booking : costumerBookings)
        currentTickets += booking.getTickets().size();
      if (currentTickets == maxTickets)
        bestCustomers.add(document.getId());
    }
    for(String s : bestCustomers)
      System.out.println(s);
    return bestCustomers;
  }

  public void confirmQuotes(List<Quote> quotes, String customer) {
    // Create a booking.
    // There is possibility that we reserved one ticket but cannot reserve some other ticket even after 5 repeats.
    int cnt = 0;
    List<Ticket> tickets = new ArrayList<>();
    for (Quote quote : quotes) {
      boolean reserved = false;
      for (int i = 0; i < repeat; i++) {
        try {
          this.theatreService.reserveSeat(quote, customer);

          reserved = true;
          Ticket currentTicket = new Ticket(quote.getCompany(), quote.getShowId(), quote.getSeatId(), UUID.randomUUID(), customer);
          System.out.println("New ticket: " + currentTicket.getTicketId().toString());
          System.out.println("New seat: " + quote.getSeatId().toString());
          tickets.add(currentTicket);
          break;
        } catch (ServiceException ex) {
          System.err.println("Seat cannot be reserved: " + quote.getSeatId());
        }
      }
      if (reserved) {
        cnt += 1;
      } else {
        // Do not continue
        break;
      }
    }
    if (cnt == quotes.size()) {
      System.out.println("All seats successfully reserved!");
      Booking booking = new Booking(UUID.randomUUID(), LocalDateTime.now(), tickets, customer);

      //Conversion to supported data types
      Map<String, Object> convertedBooking = new HashMap<>();
      convertedBooking.put("UUID", booking.getId().toString());
      convertedBooking.put("time", booking.getTime().toString());
      convertedBooking.put("customer", booking.getCustomer());

      ArrayList<Object> convertedTickets = new ArrayList<>();
      for(Ticket ticket : booking.getTickets()) {
        Map<String, Object> ticketObject = new HashMap<>();
        ticketObject.put("company", ticket.getCompany());
        ticketObject.put("showID", ticket.getShowId().toString());
        ticketObject.put("seatID", ticket.getSeatId().toString());
        ticketObject.put("ticketID", ticket.getTicketId().toString());
        ticketObject.put("customer", ticket.getCustomer());
        convertedTickets.add(ticketObject);
      }
      convertedBooking.put("tickets", convertedTickets);

      // Saving to Firestore NoSQL DB
      db.collection("ds").document(customer).collection("bookings").add(convertedBooking);
//            try {
//                System.out.println("Update time : ");
//            } catch(Exception e) {
//                e.printStackTrace();
//                System.err.println("Something went wrong when saving a booking to the database");
//            }

//            if(this.customerBookings.get(customer) == null) {
//                this.customerBookings.put(customer, new ArrayList<>());
//            }
//            this.customerBookings.get(customer).add(booking);
//        } else {
//            System.err.println("Error happened while reserving seats.");
    }
  }

}
