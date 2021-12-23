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
        FirestoreOptions firestoreOptions = null;

        String env = System.getenv("GAE_ENV");

        if(env != null && env.equals("standard")) {
          try {
            firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
                .setProjectId(projectId)
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .build();
          } catch (IOException e) {
            e.printStackTrace();
          }
        } else {
          firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
              .setProjectId(projectId)
              .setEmulatorHost("localhost:8084")
              .setCredentials(new FirestoreOptions.EmulatorCredentials())
              .build();
        }

      assert firestoreOptions != null;
      return firestoreOptions.getService();
    }
}