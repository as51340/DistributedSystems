package be.kuleuven.distributedsystems.cloud.controller;

import be.kuleuven.distributedsystems.cloud.Model;
import be.kuleuven.distributedsystems.cloud.entities.Quote;
import be.kuleuven.distributedsystems.cloud.pubsub.Message;
import be.kuleuven.distributedsystems.cloud.pubsub.requests.ConfirmQuotesRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.protobuf.ByteString;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.stream.Collectors;

@RestController
public class PubSubController {

    private final Model model;
    private final JsonParser jsonParser = new JsonParser();
    private final Gson gson = new Gson();

    @Autowired
    public PubSubController(Model model) {
        this.model = model;
    }

    @PostMapping("/pubsub")
    public void ConfirmQuotesHandler(HttpServletRequest request) throws IOException {
        ConfirmQuotesRequest confirmQuotesRequest = parseMesage(request);
        for(Quote quote: confirmQuotesRequest.getQuotes()) {
            System.out.println("Company: " + quote.getCompany());
        }
        System.out.println("Customer: " + confirmQuotesRequest.getCustomer());
        this.model.confirmQuotes(confirmQuotesRequest.getQuotes(), confirmQuotesRequest.getCustomer());
    }

    private ConfirmQuotesRequest parseMesage(HttpServletRequest request) throws IOException {
        String requestBody = request.getReader().lines().collect(Collectors.joining("\n"));
        System.out.println("Request body: " + requestBody);
        JsonElement jsonRoot = jsonParser.parse(requestBody);
        String data = jsonRoot.getAsJsonObject().get("message").getAsJsonObject().get("data").toString();
        data = decode(data.substring(1, data.length()-1));
        //System.out.println("Data: " + data);
        return gson.fromJson(data, ConfirmQuotesRequest.class);
    }

    private String decode(String data) {
        return new String(Base64.getDecoder().decode(data));
    }

}