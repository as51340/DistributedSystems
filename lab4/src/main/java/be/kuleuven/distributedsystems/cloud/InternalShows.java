package be.kuleuven.distributedsystems.cloud;

import be.kuleuven.distributedsystems.cloud.entities.Booking;
import be.kuleuven.distributedsystems.cloud.entities.Show;
import be.kuleuven.distributedsystems.cloud.jsondata.DataDTO;
import be.kuleuven.distributedsystems.cloud.jsondata.SeatDTO;
import be.kuleuven.distributedsystems.cloud.jsondata.ShowDTO;
import be.kuleuven.distributedsystems.cloud.entities.Ticket;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.gson.Gson;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
      String content = null;
      try {
        File file = ResourceUtils.getFile("classpath:data.json");
        content = new String(Files.readAllBytes(file.toPath()));
      } catch (IOException e) {
        e.printStackTrace();
      }

      Gson gson = new Gson();
      DataDTO data = gson.fromJson(content, DataDTO.class);

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
}
