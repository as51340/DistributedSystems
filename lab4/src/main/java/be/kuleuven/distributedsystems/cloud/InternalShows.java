package be.kuleuven.distributedsystems.cloud;

import be.kuleuven.distributedsystems.cloud.jsondata.DataDTO;
import be.kuleuven.distributedsystems.cloud.jsondata.SeatDTO;
import be.kuleuven.distributedsystems.cloud.jsondata.ShowDTO;
import be.kuleuven.distributedsystems.cloud.entities.Ticket;
import com.google.cloud.firestore.Firestore;
import com.google.gson.Gson;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InternalShows {

  public void initInternalShows(Firestore db) throws IOException {
    //TODO Check if they exist before adding them
    //Are the shows in the Database?
    //If no, add them and show them
    //If yes, show them

    File file = ResourceUtils.getFile("classpath:data.json");
    String content = new String(Files.readAllBytes(file.toPath()));
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
