package be.kuleuven.distributedsystems.cloud;

import be.kuleuven.distributedsystems.cloud.entities.Booking;
import be.kuleuven.distributedsystems.cloud.entities.Quote;
import be.kuleuven.distributedsystems.cloud.entities.Seat;
import be.kuleuven.distributedsystems.cloud.entities.Show;
import be.kuleuven.distributedsystems.cloud.jsondata.DataDTO;
import be.kuleuven.distributedsystems.cloud.jsondata.SeatDTO;
import be.kuleuven.distributedsystems.cloud.jsondata.ShowDTO;
import be.kuleuven.distributedsystems.cloud.entities.Ticket;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.gson.Gson;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class InternalShows {

  public void initInternalShows(Firestore db){
    ApiFuture<QuerySnapshot> query = db.collection("shows").get();
    QuerySnapshot querySnapshot = null;
    try {
      querySnapshot = query.get();
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
    assert querySnapshot != null;

    if(querySnapshot.isEmpty()) {
      InputStream ioStream = this.getClass()
          .getClassLoader()
          .getResourceAsStream("data.json");

      if (ioStream == null) {
        throw new IllegalArgumentException("data.json" + " is not found");
      }

      InputStreamReader isr = new InputStreamReader(ioStream);
      BufferedReader br = new BufferedReader(isr);

      Gson gson = new Gson();
      DataDTO data = gson.fromJson(br, DataDTO.class);

      for (ShowDTO show : data.getShows()) {
        Map<String, Object> showObject = new HashMap<>();
        showObject.put("name", show.getName());
        showObject.put("location", show.getLocation());
        showObject.put("image", show.getImage());

        String showID = UUID.randomUUID().toString();

        db.collection("shows").document(showID).set(showObject);

        for (SeatDTO seat : show.getSeats()) {
          Map<String, Object> seatObject = new HashMap<>();
          seatObject.put("name", seat.getName());
          seatObject.put("price", seat.getPrice());
          seatObject.put("time", seat.getTime());
          seatObject.put("type", seat.getType());
          seatObject.put("available", "true");

          String seatID = UUID.randomUUID().toString();
          db.collection("shows").document(showID).collection("seats").document(seatID).set(seatObject);
        }
      }
    }
  }

  public List<Show> dbJsonToPojo(Firestore db) {
    List<Show> shows = new ArrayList<>();
    ApiFuture<QuerySnapshot> query = db.collection("shows").get();
    QuerySnapshot querySnapshot = null;
    try {
      querySnapshot = query.get();
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
    assert querySnapshot != null;
    List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
    for (QueryDocumentSnapshot document : documents) {
      // documentID is showID, document is a specific show, there are two internal shows
      UUID showID = UUID.fromString(document.getId());
      String name = document.getString("name");
      String location = document.getString("location");
      String image = document.getString("image");
      Show show = new Show("internal", showID, name, location, image);
      shows.add(show);
    }
    return shows;
  }

  public Show getShow(UUID uuid, Firestore db) {
    List<Show> shows = dbJsonToPojo(db);
    Show showToReturn = null;
    for(Show show : shows) {
      if(show.getShowId().equals(uuid))
        showToReturn = show;
    }
    return showToReturn;
  }

  public List<LocalDateTime> getShowTimes(UUID showId, Firestore db) {
    Set<LocalDateTime> timesSet = new HashSet<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss");
    ApiFuture<QuerySnapshot> query = db.collection("shows").document(showId.toString()).collection("seats").get();
    QuerySnapshot querySnapshot = null;
    try {
      querySnapshot = query.get();
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
    assert querySnapshot != null;
    List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
    for (QueryDocumentSnapshot document : documents) {
      timesSet.add(LocalDateTime.parse((Objects.requireNonNull(document.getString("time"))), formatter));
    }
    return new ArrayList<>(timesSet);
  }

  public List<Seat> getAvailableSeats(UUID showId, LocalDateTime time, Firestore db) {
    List<Seat> availableSeats = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss");
    String formattedDateTime = time.format(formatter);
    ApiFuture<QuerySnapshot> query = db.collection("shows").document(showId.toString()).collection("seats").get();
    QuerySnapshot querySnapshot = null;
    try {
      querySnapshot = query.get();
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
    assert querySnapshot != null;
    List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
    for (QueryDocumentSnapshot document : documents) {
      if(Objects.equals(document.getString("available"), "true") && Objects.equals(document.getString("time"), formattedDateTime)) {
        String name = document.getString("name");
        double price = Double.parseDouble(Objects.requireNonNull(document.getString("price")));
        String type = document.getString("type");
        availableSeats.add(new Seat("internal", showId, UUID.fromString(document.getId()), time, type, name, price));
      }
    }
    return availableSeats;
  }

  public Seat getSeat(UUID showId, UUID seatId, Firestore db) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss");
    ApiFuture<DocumentSnapshot> query = db.collection("shows").document(showId.toString()).
        collection("seats").document(seatId.toString()).get();
    DocumentSnapshot documentSnapshot = null;
    try {
      documentSnapshot = query.get();
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
    assert documentSnapshot != null;

    String name = documentSnapshot.getString("name");
    double price = Double.parseDouble(Objects.requireNonNull(documentSnapshot.getString("price")));
    String type = documentSnapshot.getString("type");
    LocalDateTime time = LocalDateTime.parse((Objects.requireNonNull(documentSnapshot.getString("time"))), formatter);
    return new Seat("internal", showId, seatId, time, type, name, price);
  }

  public Ticket putTicket(Quote quote, String customer) {
    return new Ticket(quote.getCompany(), quote.getShowId(), quote.getSeatId(), UUID.randomUUID(), customer);
  }

  public void setUnavailable(List<Ticket> tickets, Firestore db) {
    for(Ticket t : tickets)
      db.collection("shows").document(t.getShowId().toString()).collection("seats")
          .document(t.getSeatId().toString()).update("available", "false");
  }
}
