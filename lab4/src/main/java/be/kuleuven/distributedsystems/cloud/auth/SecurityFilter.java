package be.kuleuven.distributedsystems.cloud.auth;

import be.kuleuven.distributedsystems.cloud.entities.Ticket;
import be.kuleuven.distributedsystems.cloud.entities.User;
import be.kuleuven.distributedsystems.cloud.services.ServiceException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.RSAKeyProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.gax.rpc.InvalidArgumentException;
import com.google.auth.oauth2.IdToken;
import com.google.auth.oauth2.IdTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;


@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private String projectId;

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
     * @return keys from gserviceaccount.com
     * @throws JsonProcessingException
     */
    private Map<String, String> getKeys() throws JsonProcessingException {
        String keysURL = "https://www.googleapis.com/robot/v1/metadata/x509/securetoken@system.gserviceaccount.com";

        ObjectMapper mapper = new ObjectMapper();
        String responseStr =  Objects.requireNonNull(webClientBuilder.baseUrl(keysURL)
                .build()
                .get()
                .retrieve()
                .onStatus(HttpStatus:: isError,
                        response -> Mono.error(new ServiceException("Error while trying to fetch keys...", response.statusCode().value())))
                .bodyToMono(String.class)
                .block());

        Map<String, String> publicKeys = mapper.readValue(responseStr, Map.class);
        return publicKeys;
    }

    private RSAPublicKey getPublicKey(String certificate) throws NoSuchAlgorithmException, InvalidKeySpecException, CertificateException {
        CertificateFactory factory = CertificateFactory.getInstance("X.509");
        InputStream in = new ByteArrayInputStream(certificate.getBytes(StandardCharsets.UTF_8));
        Certificate cer = factory.generateCertificate(in);
        return (RSAPublicKey) cer.getPublicKey();



        // String parsedCertificate = certificate.replaceAll("\\n", "")
        //         .replace("-----BEGIN CERTIFICATE-----", "")
        //         .replace("-----END CERTIFICATE-----", "");
//
        // KeyFactory kf = KeyFactory.getInstance("RSA");
        // X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(parsedCertificate));
        // RSAPublicKey pubKey = (RSAPublicKey) kf.generatePublic(keySpecX509);
        // return pubKey;
    }

    private boolean verifyIdentityToken(DecodedJWT jwt) throws IOException {
        // HEADER CHECK
        // Check algorithm
        if(!jwt.getAlgorithm().equals("RS256")) return false; // not it returns None

        // Get keyId
        Map<String, String> publicKeys = this.getKeys();
        String key = jwt.getKeyId();
        if(!publicKeys.containsKey(key)) {
            return false;
        }
        // Corresponding certificate for obtained key
        String certificate = publicKeys.get(key);

        // PAYLOAD CHECK
        long unixTime = System.currentTimeMillis(); // current time since Unix epoch
        long expTime = jwt.getExpiresAt().getTime(); // expiration time
        long iat = jwt.getIssuedAt().getTime();

        if(expTime < unixTime) {
            System.out.println("Expiration time fail..." + expTime);
            System.out.println("Unix time: " + unixTime);
            return false;
        }
        if(iat > unixTime) {
            System.out.println("Issued at time fail..." + iat);
            System.out.println("Unix time: " + unixTime);
            return false;
        }

        long auth_time = jwt.getClaims().get("auth_time").asLong();
        if(auth_time > unixTime) {
            System.out.println("Auth time: " + auth_time);
            return false;
        }

        List<String> aud = jwt.getAudience();
        if(aud.size() > 1 || !aud.get(0).equals(projectId)) {
            System.out.println("Audience fail...");
            return false;
        }
        String iss = jwt.getIssuer();
        if(!iss.equals("https://securetoken.google.com/" + projectId)) { // check if this is true
            System.out.println("Issuer fail... " + iss);
            return false;
        }
        String sub = jwt.getSubject();
        if(sub.isBlank() || sub.isEmpty()) {
            System.out.println("Subject fail..." + sub);
            return false;
        }

        // CHECK SIGNATURE OF THE ID TOKEN

        System.out.println("ID: " + jwt.getId());
        System.out.println("Token: " + jwt.getToken());
        System.out.println("Signature: " + jwt.getSignature());

        try {
            Algorithm algorithm = Algorithm.RSA256(getPublicKey(certificate), null);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("https://securetoken.google.com/" + projectId)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt_verifier = verifier.verify(jwt.getToken());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | JWTVerificationException | CertificateException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        var session = WebUtils.getCookie(request, "session");
        if (session != null) {

            String token = session.getValue();
            DecodedJWT jwt = this.decodeIdentityToken(token);
            String email = jwt.getClaim("email").asString(); // here you can also choose email_verified
            String role = jwt.getClaim("role").asString(); // you need to setup role in firebase emulator
            User user = new User(email, role);;
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(new FirebaseAuthentication(user));

            // Check identity token
            if(!this.verifyIdentityToken(jwt)) {
                throw new ServletException("Verifying identity token failed!");
            }
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

