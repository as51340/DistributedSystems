package be.kuleuven.distributedsystems.cloud.controller;

import be.kuleuven.distributedsystems.cloud.entities.Quote;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public final class Cart {
    public static List<Quote> fromCookie(String cartString) {
        if (cartString == null || cartString.equals("")) {
            return new ArrayList<>();
        }
        try {
            return new ObjectMapper().readValue(
                    new String(Base64.getDecoder().decode(cartString)), new TypeReference<>() {
                    });
        } catch (JsonProcessingException e) {
            return new ArrayList<>();
        }
    }

    public static ResponseCookie toCookie(List<Quote> cart) {
        try {
            return ResponseCookie.from("cart",
                            Base64.getEncoder().encodeToString(
                                    new ObjectMapper().writeValueAsString(cart).getBytes()))
                    .httpOnly(true)
                    .sameSite("Lax")
                    .path("/")
                    .maxAge(60 * 60)
                    .build();
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
