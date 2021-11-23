package be.kuleuven.distributedsystems.cloud.services;

import be.kuleuven.distributedsystems.cloud.entities.Quote;
import be.kuleuven.distributedsystems.cloud.entities.Show;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

}
