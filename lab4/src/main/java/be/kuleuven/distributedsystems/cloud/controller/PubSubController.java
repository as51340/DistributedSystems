package be.kuleuven.distributedsystems.cloud.controller;

import be.kuleuven.distributedsystems.cloud.Model;
import be.kuleuven.distributedsystems.cloud.entities.Quote;
import be.kuleuven.distributedsystems.cloud.pubsub.requests.ConfirmQuotesRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PubSubController {

    private final Model model;
    private final JsonParser jsonParser = new JsonParser();
    private final Gson gson = new Gson();
    private Set<String> messageIDs = new HashSet<>();

    @Autowired
    public PubSubController(Model model) {
        this.model = model;
    }

    @PostMapping("/pubsub")
    public ResponseEntity<Void> ConfirmQuotesHandler(HttpServletRequest request) throws IOException {

        String requestBody = request.getReader().lines().collect(Collectors.joining("\n"));
        // System.out.println("Request body: " + requestBody);
        JsonElement jsonRoot = jsonParser.parse(requestBody);
        if(!this.messageIDs.add(this.getMessageId(jsonRoot))) { // because this message was already received
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }
        ConfirmQuotesRequest confirmQuotesRequest = this.getData(jsonRoot);

        for(String messageId: this.messageIDs) {
            System.out.print(messageId + " ");
        }
        System.out.println();
        this.model.confirmQuotes(confirmQuotesRequest.getQuotes(), confirmQuotesRequest.getCustomer());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String getMessageId(JsonElement jsonRoot) {
        String messageId = jsonRoot.getAsJsonObject().get("message").getAsJsonObject().get("messageId").toString();
        messageId = messageId.substring(1, messageId.length()-1);
        System.out.println("After parsing message: " + messageId);
        return messageId;
    }

    private ConfirmQuotesRequest getData(JsonElement jsonRoot) {
        String data = jsonRoot.getAsJsonObject().get("message").getAsJsonObject().get("data").toString();
        data = decode(data.substring(1, data.length()-1));
        return gson.fromJson(data, ConfirmQuotesRequest.class);
    }

    private String decode(String data) {
        return new String(Base64.getDecoder().decode(data));
    }

}