package be.kuleuven.distributedsystems.cloud.controller;

import be.kuleuven.distributedsystems.cloud.entities.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthController {
    @PostMapping("/authenticate")
    public ResponseEntity<Void> authenticate(HttpServletRequest request, @RequestBody String idToken) {
        ResponseCookie sessionCookie = ResponseCookie.from("session", idToken)
                .httpOnly(true)
                .secure(true)
                .sameSite("Lax")
                .path("/")
                .maxAge(60 * 60)
                .build();
        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, sessionCookie.toString())
                .build();
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader(value = "referer") String referer) {
        ResponseCookie sessionCookie = ResponseCookie.from("session", "")
                .httpOnly(true)
                .secure(true)
                .sameSite("Lax")
                .path("/")
                .maxAge(0)
                .build();
        ResponseCookie cartCookie = ResponseCookie.from("cart", "")
                .httpOnly(true)
                .secure(true)
                .sameSite("Lax")
                .path("/")
                .maxAge(0)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, sessionCookie.toString());
        headers.add(HttpHeaders.SET_COOKIE, cartCookie.toString());
        headers.add(HttpHeaders.LOCATION, referer);
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    public static User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
