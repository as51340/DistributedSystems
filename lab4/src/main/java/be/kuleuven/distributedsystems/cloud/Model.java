package be.kuleuven.distributedsystems.cloud;

import be.kuleuven.distributedsystems.cloud.entities.*;
import be.kuleuven.distributedsystems.cloud.pubsub.PubSubHandler;
import be.kuleuven.distributedsystems.cloud.services.SendGridEmail;
import be.kuleuven.distributedsystems.cloud.services.ServiceException;
import be.kuleuven.distributedsystems.cloud.services.TheatreService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ExecutionException;

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
  private final Firestore db;
  private final InternalShows internalShows;

  public Model(TheatreService theatreService, SendGridEmail sendGrid) {
    this.theatreService = theatreService;
    this.sendGrid = sendGrid;

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

    public Ticket getTicket(String company, UUID showId, UUID seatId) {
      Ticket ticket = null;
      for (int i = 0; i < repeat; i++) {
        try {
          ticket = this.theatreService.getTicket(company, showId, seatId);
          break;
        } catch (ServiceException ex) {
          // System.out.println(ex.getMessage());
        }
      }
      return ticket;
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
                    //System.out.println("showID:" + t.getShowId() + "\nseatID:" + t.getSeatId()
                    //                  + "\nticketID:" + t.getTicketId() + "\ncompany:" + t.getCompany());
                    ticketList.add(t);
                  }
                }
              // Extract all other booking data
              UUID uuid = UUID.fromString(Objects.requireNonNull(document.getString("UUID")));
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss");
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
            ApiFuture<QuerySnapshot> query = db.collection("ds").get();
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents)
              allBookings.addAll(getBookings(document.getId()));
            return allBookings;
          }

          public Set<String> getBestCustomers() throws ExecutionException, InterruptedException {
            Set<String> bestCustomers = new HashSet<>();
            int maxTickets = 0;
            List<Booking> costumerBookings;
            ApiFuture<QuerySnapshot> query = db.collection("ds").get();
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

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
                  // System.out.println("Put ticket: ");
                  // System.out.println("Show ID: " + currentTicket.getShowId().toString());
                  // System.out.println("Seat ID: " + currentTicket.getSeatId().toString());
                  // System.out.println("Ticket ID: " + currentTicket.getTicketId().toString());
                  // System.out.println("Company: " + currentTicket.getCompany());
                  // System.out.println("Customer: " + currentTicket.getCustomer());
                  // System.out.println();
                  // Ticket getTicket = this.theatreService.getTicket(quote.getCompany(), quote.getShowId(), quote.getSeatId());
                  // System.out.println("Get ticket: ");
                  // System.out.println("Show ID: " + getTicket.getShowId().toString());
                  // System.out.println("Seat ID: " + getTicket.getSeatId().toString());
                  // System.out.println("Ticket ID: " + getTicket.getTicketId().toString());
                  // System.out.println("Company: " + getTicket.getCompany());
                  // System.out.println("Customer: " + getTicket.getCustomer());
                  // System.out.println();
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
              // TODO delete already reserved tickets
              System.err.println("Error happened while reserving seats, deleting old tickets. Cnt: " + cnt);
              for(Ticket ticket: tickets) {
                boolean deleted = false;
                for(int i = 0; i < repeat; i++) {
                  try {
                    String res = this.theatreService.deleteTicket(ticket);
                    System.out.println("Delete result");
                    System.out.println(res);
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
              sendGrid.sendEmail("Ticket reservation failure", "Dear customer, your tickets couldn't be processed, please try to do again or feel free to contact us for further questions...",
                  customer);
            }
          }
}
