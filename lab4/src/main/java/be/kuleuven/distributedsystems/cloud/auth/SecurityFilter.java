package be.kuleuven.distributedsystems.cloud.auth;

import be.kuleuven.distributedsystems.cloud.entities.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.api.gax.rpc.InvalidArgumentException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.WebUtils;
import reactor.core.publisher.Mono;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.util.*;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    /**
     * Decodes identity token and returns with which we can work more easily
     * @param token
     * @return
     */
    private DecodedJWT decodeIdentityToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt;
        } catch (JWTDecodeException exception){
            System.err.println("Error happened while trying to decode token!");
        }
        return null;
    }

    /**
     * Checks if keyID corresponds to one of the public keys from https://www.googleapis.com/robot/v1/metadata/x509/securetoken@system.gserviceaccount.com
     * @param keyId
     * @return true if okay, false otherwise
     */
    private boolean checkKeyId(String keyId) {
        return false;
    }

    private void getKeys() {
        String keysURL = "https://www.googleapis.com/robot/v1/metadata/x509/securetoken@system.gserviceaccount.com";
        RestTemplate template = new RestTemplate();
        HttpEntity<Map> response = template.exchange(keysURL, HttpMethod.POST, null, Map.class);

        Map<String, String> result = response.getBody();
        HttpHeaders headers = response.getHeaders();

        // get some cache control
    }

    private boolean verifyIdentityToken(DecodedJWT jwt) {
        // Check algorith
        System.out.println("Algorithm: " + jwt.getAlgorithm());
        // if(!jwt.getAlgorithm().equals("RS256")) return false; // not it returns None

        // Get keyId
        this.getKeys();
        String key = jwt.getKeyId();
        // System.out.println("My real key");
        // System.out.println(key);
        // check them somehow

        long expTime = jwt.getExpiresAt().getTime();
        long iat = jwt.getIssuedAt().getTime();
        List<String> aud = jwt.getAudience();
        String iss = jwt.getIssuer();
        String sub = jwt.getSubject();

        return true;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        var session = WebUtils.getCookie(request, "session");
        if (session != null) {
            // TODO: (level 1) decode Identity Token and assign correct email and role
            String token = session.getValue();
            DecodedJWT jwt = this.decodeIdentityToken(token);
            String email = jwt.getClaim("email").asString(); // here you can also choose email_verified
            String role = jwt.getClaim("role").asString(); // you need to setup role in firebase emulator
            User user = new User(email, role);;
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(new FirebaseAuthentication(user));
            // TODO: (level 2) verify Identity Token

            // Check identity token
            //if(!this.verifyIdentityToken(jwt)) {
            //    throw new ServletException("Verifying identity token failed!");
            //}
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI().substring(request.getContextPath().length());
        return path.equals("/authenticate") || path.endsWith(".html") || path.endsWith(".js") || path.endsWith(".css");
    }

    private static class FirebaseAuthentication implements Authentication {
        private final User user;

        FirebaseAuthentication(User user) {
            this.user = user;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            if (user.isManager()) {
                return List.of(new SimpleGrantedAuthority("manager"));
            } else{
                return new ArrayList<>();
            }
        }

        @Override
        public Object getCredentials() {
            return null;
        }

        @Override
        public Object getDetails() {
            return null;
        }

        @Override
        public User getPrincipal() {
            return this.user;
        }

        @Override
        public boolean isAuthenticated() {
            return true;
        }

        @Override
        public void setAuthenticated(boolean b) throws IllegalArgumentException {

        }

        @Override
        public String getName() {
            return null;
        }
    }
}

