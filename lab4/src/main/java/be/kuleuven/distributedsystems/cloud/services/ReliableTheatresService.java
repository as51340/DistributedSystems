package be.kuleuven.distributedsystems.cloud.services;

import be.kuleuven.distributedsystems.cloud.entities.Quote;
import be.kuleuven.distributedsystems.cloud.entities.Seat;
import be.kuleuven.distributedsystems.cloud.entities.Show;
import be.kuleuven.distributedsystems.cloud.entities.Ticket;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ReliableTheatresService {

    private final String reliableTheatresURL = "https://reliabletheatrecompany.com";
    private final String API_KEY = "wCIoTqec6vGJijW2meeqSokanZuqOL";


    @Autowired
    WebClient.Builder webClientBuilder;

    /**
     * Dependency injection
     */
    public ReliableTheatresService() {

    }

    public List<Show> getShows() {
        var shows = webClientBuilder.baseUrl(reliableTheatresURL)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("shows")
                        .queryParam("key", API_KEY)
                        .build())
                .retrieve()

                .bodyToMono(new ParameterizedTypeReference<CollectionModel<Show>>() {
                })
                .block()
                .getContent();
        return new ArrayList<>(shows);
    }

    public Show getShow(String company, UUID showId) {
        return webClientBuilder.baseUrl(reliableTheatresURL)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("shows")
                        .pathSegment("/" + showId)
                        .queryParam("key", API_KEY)
                        .build())
                .retrieve()

                .bodyToMono(new ParameterizedTypeReference<Show>() {
                })
                .block();
    }

    public List<LocalDateTime> getShowTimes(String company, UUID showId) {
        var times = Objects.requireNonNull(webClientBuilder.baseUrl(reliableTheatresURL)
                        .build()
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .pathSegment("shows")
                                .pathSegment(showId.toString())
                                .pathSegment("/times")
                                .queryParam("key", API_KEY)
                                .build())
                        .retrieve()

                        .bodyToMono(new ParameterizedTypeReference<CollectionModel<LocalDateTime>>() {
                        })
                        .block())
                .getContent();
        return new ArrayList<>(times);
    }

    public List<Seat> getAvailableSeats(String company, UUID showId, LocalDateTime time) {
        var times = Objects.requireNonNull(webClientBuilder.baseUrl(reliableTheatresURL)
                        .build()
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .pathSegment("shows")
                                .pathSegment(showId.toString())
                                .pathSegment("seats")
                                .queryParam("time", time.toString())
                                .queryParam("available", true)
                                .queryParam("key", API_KEY)
                                .build())
                        .retrieve()

                        .bodyToMono(new ParameterizedTypeReference<CollectionModel<Seat>>() {
                        })
                        .block())
                .getContent();
        return new ArrayList<>(times);
    }

    public Seat getSeat(String company, UUID showId, UUID seatId) {
        return webClientBuilder.baseUrl(reliableTheatresURL)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("shows")
                        .pathSegment("/" + showId)
                        .queryParam("key", API_KEY)
                        .build())
                .retrieve()

                .bodyToMono(new ParameterizedTypeReference<Seat>() {
                })
                .block();
    }

    public Ticket getTicket(String company, UUID showId, UUID seatId) {
        return webClientBuilder.baseUrl(reliableTheatresURL)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("ticket")
                        .pathSegment("/" + showId)
                        .queryParam("key", API_KEY)
                        .build())
                .retrieve()

                .bodyToMono(new ParameterizedTypeReference<Ticket>() {
                })
                .block();
    }
}
