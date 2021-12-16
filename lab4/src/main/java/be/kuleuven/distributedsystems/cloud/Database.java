package be.kuleuven.distributedsystems.cloud;
import be.kuleuven.distributedsystems.cloud.entities.ShowDTO;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;

public class Database {

    @Autowired
    private String projectId;

    public Firestore initDB() {
      FirestoreOptions firestoreOptions =
              null;
      try {
          firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
                  .setProjectId(projectId)
                  //.setEmulatorHost("localhost:8084")
                  .setCredentials(GoogleCredentials.getApplicationDefault())//new FirestoreOptions.EmulatorCredentials())
                  .build();
      } catch (IOException e) {
          e.printStackTrace();
          System.err.println("Problem within the database class");
      }
      assert firestoreOptions != null;
      return firestoreOptions.getService();
    }

    public void initInternalShows() throws IOException {
      //Are the shows in the Database?
      //If no, add them and show them
      //If yes, show them

      File file = ResourceUtils.getFile("classpath:data.json");

      System.out.println("File Found : " + file.exists());

      String content = new String(Files.readAllBytes(file.toPath()));
      System.out.println(content);

      Gson gson = new Gson();
      //ShowDTO show = gson.fromJson(content, ShowDTO.class);

    }
}