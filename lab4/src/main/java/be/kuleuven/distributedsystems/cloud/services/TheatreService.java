package be.kuleuven.distributedsystems.cloud.services;

import be.kuleuven.distributedsystems.cloud.entities.Quote;
import be.kuleuven.distributedsystems.cloud.entities.Seat;
import be.kuleuven.distributedsystems.cloud.entities.Show;
import be.kuleuven.distributedsystems.cloud.entities.Ticket;
import io.netty.handler.ssl.SslContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TheatreService {

    @Autowired
    WebClient.Builder webClientBuilder;

    private final String reliableTheatreURL = "https://reliabletheatrecompany.com";
    private final String unreliableTheatreURL = "https://unreliabletheatrecompany.com";

    private final List<String> URLs = new ArrayList<>();
    private final String API_KEY = "wCIoTqec6vGJijW2meeqSokanZuqOL";


    /**
     * Dependency injection
     */
    public TheatreService() {
        URLs.add(reliableTheatreURL);
        URLs.add(unreliableTheatreURL);
    }

    public List<Show> getShows() {
        List<Show> shows = new ArrayList<>();
        for(String url : URLs) {
            var show = webClientBuilder.baseUrl(url)
                    .build()
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .pathSegment("shows")
                            .queryParam("key", API_KEY)
                            .build())
                    .retrieve()
                    .onStatus(HttpStatus :: isError,
                    response -> Mono.error(new ServiceException("Error while trying to fetch shows", response.statusCode().value())))
                    .bodyToMono(new ParameterizedTypeReference<CollectionModel<Show>>() {
                    })
                    .block()
                    .getContent();
            shows.addAll(show);
        }
        return shows;
    }

    public Show getShow(String company, UUID showId) {
        return webClientBuilder.baseUrl("https://" + company)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("shows")
                        .pathSegment(showId.toString())
                        .queryParam("key", API_KEY)
                        .build())
                .retrieve()
                .onStatus(HttpStatus :: isError,
                        response -> Mono.error(new ServiceException("Error while trying to fetch show " + showId.toString(), response.statusCode().value())))
                .bodyToMono(new ParameterizedTypeReference<Show>() {
                })
                .block();
    }

    // Correct this
    public List<LocalDateTime> getShowTimes(String company, UUID showId) {
        var times = Objects.requireNonNull(webClientBuilder.baseUrl("https://" + company)
                        .build()
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .pathSegment("shows")
                                .pathSegment(showId.toString())
                                .pathSegment("/times")
                                .queryParam("key", API_KEY)
                                .build())
                        .retrieve()
                        .onStatus(HttpStatus :: isError,
                                response -> Mono.error(new ServiceException("Error while trying to fetch show times", response.statusCode().value())))
                        .bodyToMono(new ParameterizedTypeReference<CollectionModel<LocalDateTime>>() {
                        })
                        .block())
                .getContent();
        return new ArrayList<>(times);
    }

    public Seat getSeat(String company, UUID showId, UUID seatId) {
        return webClientBuilder.baseUrl("https://" + company)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("shows")
                        .pathSegment(showId.toString())
                        .pathSegment("seats")
                        .pathSegment(seatId.toString())
                        .queryParam("key", API_KEY)
                        .build())
                .retrieve()
                .onStatus(HttpStatus :: isError,
                        response -> Mono.error(new ServiceException("Error while trying to fetch seat " + seatId.toString(), response.statusCode().value())))
                .bodyToMono(new ParameterizedTypeReference<Seat>() {
                })
                .block();
    }

    public List<Seat> getAvailableSeats(String company, UUID showId, LocalDateTime time) {
        var times = Objects.requireNonNull(webClientBuilder.baseUrl("https://" + company)
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
                        .onStatus(HttpStatus :: isError,
                                response -> Mono.error(new ServiceException("Error while trying to fetch available seats", response.statusCode().value())))
                        .bodyToMono(new ParameterizedTypeReference<CollectionModel<Seat>>() {
                        })
                        .block())
                .getContent();

            return new ArrayList<>(times);
    }

    /**
     * @param quote
     * @param customer
     * @return true if everything went well, false otherwise
     */

    public Seat reserveSeat(Quote quote, String customer) {
        Seat seat = Objects.requireNonNull(webClientBuilder.baseUrl("https://" + quote.getCompany())
                .build()
                .put()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("shows")
                        .pathSegment(quote.getShowId().toString())
                        .pathSegment("seats")
                        .pathSegment(quote.getSeatId().toString())
                        .pathSegment("ticket")
                        .queryParam("customer", customer)
                        .queryParam("key", API_KEY)
                        .build())
                .retrieve()
                .onStatus(HttpStatus :: isError,
                        response -> Mono.error(new ServiceException("Error while trying to reserve seat" +
                                quote.getSeatId().toString(), response.statusCode().value())))
                .bodyToMono(new ParameterizedTypeReference<Seat>() {
                })
                .block());

        this.getTicket(quote.getCompany(), quote.getShowId(), quote.getSeatId());
        System.out.println("Temp ticket properties if any");

        //System.out.println("Company: " + tempTicket.getCompany());
        return seat;
    }

    public Ticket getTicket(String company, UUID showId, UUID seatId) {
       return webClientBuilder.baseUrl(company)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("shows")
                        .pathSegment(showId.toString())
                        .pathSegment("seats")
                        .pathSegment(seatId.toString())
                        .pathSegment("ticket")
                        .queryParam("key", API_KEY)
                        .build())
                .retrieve()

                .bodyToMono(new ParameterizedTypeReference<Ticket>() {
                })
                .block();
    }
}