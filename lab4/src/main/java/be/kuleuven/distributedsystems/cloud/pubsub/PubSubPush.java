package be.kuleuven.distributedsystems.cloud.pubsub;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.Base64;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// [START gae_flex_pubsub_push]
@WebServlet(value = "/pubsub")
public class PubSubPush extends HttpServlet {

    private final Gson gson = new Gson();
    private final JsonParser jsonParser = new JsonParser();

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        // parse message object from "message" field in the request body json
        // decode message data from base64
        System.out.println("Receiving some message!!!");
        //Message message = getMessage(req);
        try {
            // 200, 201, 204, 102 status codes are interpreted as success by the Pub/Sub system
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    // [END gae_flex_pubsub_push]

    private Message getMessage(HttpServletRequest request) throws IOException {
        String requestBody = request.getReader().lines().collect(Collectors.joining("\n"));
        JsonElement jsonRoot = jsonParser.parse(requestBody);
        String messageStr = jsonRoot.getAsJsonObject().get("message").toString();
        Message message = gson.fromJson(messageStr, Message.class);

        String decoded = decode(message.getData());
        message.setData(decoded);
        return message;
    }

    private String decode(String data) {
        return new String(Base64.getDecoder().decode(data));
    }
}