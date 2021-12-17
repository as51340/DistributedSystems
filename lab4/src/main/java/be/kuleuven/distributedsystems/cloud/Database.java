package be.kuleuven.distributedsystems.cloud;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

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
}