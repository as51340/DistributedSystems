package be.kuleuven.distributedsystems.cloud;

import be.kuleuven.distributedsystems.cloud.entities.*;
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
  private final TheatreService theatreService;

  // SendGrid setup
  private final SendGridEmail sendGrid;

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
    if (!company.equals("internal")) {
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
    if (!company.equals("internal")) {
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
    if (!company.equals("internal")) {
      for (int i = 0; i < repeat; i++) {
        try {
          seats = this.theatreService.getAvailableSeats(company, showId, time);
          break;
        } catch (ServiceException ex) {
          ex.printStackTrace();
        }
      }
    } else {
      seats = internalShows.getAvailableSeats(showId, time, db);
    }
    if (seats == null) {
      return new ArrayList<>();
    }
    return seats;
  }

  public Seat getSeat(String company, UUID showId, UUID seatId) {
    Seat seat = null;
    if (!company.equals("internal")) {
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
    ApiFuture<QuerySnapshot> query = db.collection("user").document(customer).collection("bookings").get();
    QuerySnapshot querySnapshot = query.get();
    List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
    for (QueryDocumentSnapshot document : documents) {
      // Get all tickets that are saved in the booking and create a list from them
      Map<String, Object> map = document.getData();
      List<Ticket> ticketList = new ArrayList<>();
      for (Map.Entry<String, Object> entry : map.entrySet())
        if (entry.getKey().equals("tickets")) {
          List<Map<String, Object>> ticketListDb = (List<Map<String, Object>>) entry.getValue();
          for (Map<String, Object> l : ticketListDb) {
            UUID showID = UUID.fromString((String) l.get("showID"));
            UUID seatID = UUID.fromString((String) l.get("seatID"));
            UUID ticketID = UUID.fromString((String) l.get("ticketID"));
            String company = (String) l.get("company");
            Ticket t = new Ticket(company, showID, seatID, ticketID, customer);
            ticketList.add(t);
          }
        }
      // Extract all other booking data
      UUID uuid = UUID.fromString(Objects.requireNonNull(document.getString("UUID")));
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss");
      LocalDateTime time = LocalDateTime.parse(Objects.requireNonNull(document.getString("time")), formatter);

      bookings.add(new Booking(uuid, time, ticketList, customer));
    }
    return bookings;
  }

  public List<Booking> getAllBookings() throws ExecutionException, InterruptedException {
    List<Booking> allBookings = new ArrayList<>();
    ApiFuture<QuerySnapshot> query = db.collection("user").get();
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
    ApiFuture<QuerySnapshot> query = db.collection("user").get();
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
    return bestCustomers;
  }

  public void confirmQuotes(List<Quote> quotes, String customer) {
    int cnt = 0;
    List<Ticket> tickets = new ArrayList<>();
    for(Quote quote: quotes) {
      boolean reserved = false;
      for(int i = 0; i < repeat; i++) {
        try {
          Ticket currentTicket;
          if(!quote.getCompany().equals("internal"))
            currentTicket = this.theatreService.putTicket(quote, customer);
          else
            currentTicket = internalShows.putTicket(quote, customer);
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
      Booking booking = new Booking(UUID.randomUUID(), LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), tickets, customer);

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
      Map<String ,Object> dummyMap= new HashMap<>();

      //Assure that no two users can buy the same two tickets
      db.runTransaction(transaction -> {
        boolean reservation = true;
        for(Quote quote: quotes) {
          ApiFuture<DocumentSnapshot> shows = db.collection("shows").document(quote.getShowId().toString())
              .collection("seats").document(quote.getSeatId().toString()).get();
          DocumentSnapshot documentSnapshot = shows.get();
          if(Objects.equals(documentSnapshot.get("available"), "false"))
            reservation = false;
        }

        if(reservation) {
          DocumentReference users = db.collection("user").document(customer);
          users.set(dummyMap);  // add empty field, won't show in console
          users.collection("bookings").add(convertedBooking);

          //Set all booked seat availabilities to false
          internalShows.setUnavailable(tickets, db);
          sendGrid.sendEmail("Ticket reservation success",
              this.getSuccessfulMailContent(tickets, customer), customer);
        } else {
          sendGrid.sendEmail("Ticket reservation failure",
              this.getUnSuccessfulMailContent(quotes, customer),
              customer);
        }
        return null;
      });
    } else {
      System.err.println("Error happened while reserving seats, deleting old tickets. Cnt: " + cnt);
      for(Ticket ticket: tickets) {
        boolean deleted = false;
        for(int i = 0; i < repeat; i++) {
          try {
            if(!ticket.getCompany().equals("internal"))
              this.theatreService.deleteTicket(ticket);
            System.out.println("Deleted ticket: " + ticket.getTicketId().toString());
            deleted = true;
            break;
          } catch(ServiceException | NullPointerException ex) {
            ex.printStackTrace();
          }
        }
        if(!deleted) {
          System.out.println("Failed to delete old ticket, further action needed...");
        }
      }
      sendGrid.sendEmail("Ticket reservation failure",
              this.getUnSuccessfulMailContent(quotes, customer),
              customer);
    }
  }

  private String getUnSuccessfulMailContent(List<Quote> quotes, String customer) {
    StringBuilder headBuilder = new StringBuilder()
            .append("<head>")
            .append("</head>");
    StringBuilder seatsStr = new StringBuilder()
            .append("<ol>");
    for (Quote quote : quotes) {
      seatsStr.append("<li>").append(quote.getSeatId()).append("</li>");
    }
    seatsStr.append("</ol>");

    int mnk = customer.indexOf('@');
    String customerUsername = customer.substring(0, mnk);

    StringBuilder bodyBuilder = new StringBuilder()
            .append("<body>")
            .append(customerUsername).append(", </br>Your request for seats\n").append(seatsStr)
            .append("couldn't be executed. Please try again on our website or contact us if problems continue.")
            .append("<br/><br/>")
            .append("Sincerely, DNet team(Daniel and Andi)")
            .append("</body>");

    StringBuilder finalbuilder = new StringBuilder()
            .append("<html><!DOCTYPE html>")
            .append(headBuilder)
            .append(bodyBuilder)
            .append("</html>");
    return finalbuilder.toString();
  }


  private String getSuccessfulMailContent(List<Ticket> tickets, String customer) {
    StringBuilder headBuilder = new StringBuilder(
            "<head>\n" +
                    "<style type=\"text/css\">\n" +
                    ".tg  {border-collapse:collapse;border-spacing:0;}\n" +
                    ".tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;\n" +
                    "  overflow:hidden;padding:10px 5px;word-break:normal;}\n" +
                    ".tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;\n" +
                    "  font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}\n" +
                    ".tg .tg-0lax{text-align:left;vertical-align:top}\n" +
                    "</style>\n").append("</head>");

    StringBuilder ticketsStr = new StringBuilder(
            "<table class=\"tg\">\n" +
                    "<thead>\n" +
                    "  <tr>\n" +
                    "    <td class=\"tg-0lax\">Customer</td>\n" +
                    "    <td class=\"tg-0lax\">Company</td>\n" +
                    "    <td class=\"tg-0lax\">ShowID</td>\n" +
                    "    <td class=\"tg-0lax\">TicketID</td>\n" +
                    "  </tr>\n" +
                    "</thead>\n" +
                    "<tbody>\n");

    StringBuilder seatsStr = new StringBuilder("<ol>\n");
    for (Ticket ticket : tickets) {
      seatsStr.append("\t<li>").append(ticket.getSeatId()).append("</li>\n");
      ticketsStr.append("\t<tr>").
              append("<td>").append(ticket.getCustomer()).append("</td>")
              .append("<td>").append(ticket.getCompany()).append("</td>")
              .append("<td>").append(ticket.getShowId()).append("</td>")
              .append("<td>").append(ticket.getTicketId()).append("</td>")
              .append("</tr>\n");

    }
    seatsStr.append("</ol>\n");
    ticketsStr.append("</tbody>\n");
    ticketsStr.append("</table>\n");


    int mnk = customer.indexOf('@');
    String customerUsername = customer.substring(0, mnk);


    StringBuilder bodyBuilder = new StringBuilder("<body>")
            .append("Dear ")
            .append(customerUsername).append(", <br/>Your request for seats\n").append(seatsStr)
            .append("has been successfully processed. Below you can find the summary of your bought tickets:\r\n\r\n")
            .append(ticketsStr)
            .append("<br/></br/>Sincerely, DNet team(Daniel and Andi)")
            .append("</body>");


    StringBuilder finalStr = new StringBuilder();

    finalStr.append("<html>\r\n<!DOCTYPE html>\r\n<body>\r\n").append(headBuilder).append(bodyBuilder).append("</html>");

    return finalStr.toString();
  }
}