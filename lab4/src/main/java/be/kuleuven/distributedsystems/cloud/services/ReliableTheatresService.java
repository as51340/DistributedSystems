package be.kuleuven.distributedsystems.cloud.services;

import be.kuleuven.distributedsystems.cloud.entities.Quote;
import be.kuleuven.distributedsystems.cloud.entities.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReliableTheatresService {

    private final String reliableTheatresURL = "https://reliabletheatrecompany.com";

    @Autowired
    WebClient.Builder webClientBuilder;

    /**
     * Dependency injection
     */
    public ReliableTheatresService() {

    }

    public List<Show> getShows() {

        /**
         * Performing blocking request
         */
        WebClient webClient = webClientBuilder.baseUrl(reliableTheatresURL).build();
        //Show[] shows =  webClient.get().uri("/shows?key=wCIoTqec6vGJijW2meeqSokanZuqOL").retrieve().bodyToMono(Show[].class).block();
        Mono<Show[]> loc_shows = webClient.get().uri("/shows?key=wCIoTqec6vGJijW2meeqSokanZuqOL")
                        .retrieve().bodyToMono(Show[].class).log();
        // Show[] shows = (Show[]) loc_shows.block();
        Object[] shows = loc_shows.block();

        System.out.println("Printing shows!");
        //System.out.println(shows)
        // for(Show show: shows) {
        //     System.out.println(show.getName());
        // }
        //return Arrays.asList(shows);
        return new ArrayList<>();
    }

}
