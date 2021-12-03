package be.kuleuven.distributedsystems.cloud.controller;

import be.kuleuven.distributedsystems.cloud.Model;
import be.kuleuven.distributedsystems.cloud.entities.Quote;
import be.kuleuven.distributedsystems.cloud.entities.Seat;
import be.kuleuven.distributedsystems.cloud.pubsub.PubSubHandler;
import be.kuleuven.distributedsystems.cloud.pubsub.requests.ConfirmQuotesRequest;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class APIController {
    private final Model model;
    private final PubSubHandler pubSubHandler;

    private final String confirmQuotesTopicID = "confirmQuotes";
    private final String confirmQuotesSubscriptionID = "confirmQuotesSubscription";

    @Autowired
    public boolean isProduction;

    @Autowired
    public APIController(Model model, PubSubHandler pubSubHandler) {
        this.model = model;
        this.pubSubHandler = pubSubHandler;
    }

    @PostConstruct
    public void init() throws IOException {
        System.out.println("PostConstruct API: " + isProduction);
        if(!isProduction) {
            this.pubSubHandler.createTopic(confirmQuotesTopicID);
        }
        this.pubSubHandler.createPushSubscriptionExample(confirmQuotesSubscriptionID, confirmQuotesTopicID);
    }

    @PostMapping(path = "/addToCart", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<Void> addToCart(
            @ModelAttribute Quote quote,
            @RequestHeader(value = "referer") String referer,
            @CookieValue(value = "cart", required = false) String cartString) {
        List<Quote> cart = Cart.fromCookie(cartString);
        cart.add(quote);
        ResponseCookie cookie = Cart.toCookie(cart);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, cookie.toString());
        headers.add(HttpHeaders.LOCATION, referer);
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @PostMapping("/removeFromCart")
    public ResponseEntity<Void> removeFromCart(
            @ModelAttribute Quote quote,
            @RequestHeader(value = "referer") String referer,
            @CookieValue(value = "cart", required = false) String cartString) {
        List<Quote> cart = Cart.fromCookie(cartString);
        cart.remove(quote);
        ResponseCookie cookie = Cart.toCookie(cart);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, cookie.toString());
        headers.add(HttpHeaders.LOCATION, referer);
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @PostMapping("/confirmCart")
    public ResponseEntity<Void> confirmCart(
            @CookieValue(value = "cart", required = false) String cartString) throws Exception {
        List<Quote> cart = Cart.fromCookie(cartString);

        ConfirmQuotesRequest confirmQuotesRequest = new ConfirmQuotesRequest(cart, AuthController.getUser().getEmail());
        this.pubSubHandler.publishWithErrorHandlerExample(confirmQuotesTopicID, confirmQuotesRequest);

        // Remember this because you will need to get your email also.
        // this.model.confirmQuotes(new ArrayList<>(cart), AuthController.getUser().getEmail());
        cart.clear();
        ResponseCookie cookie = Cart.toCookie(cart);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, cookie.toString());
        headers.add(HttpHeaders.LOCATION, "/account");
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
        // I would say that this method should definitely be publisher
    }
}
