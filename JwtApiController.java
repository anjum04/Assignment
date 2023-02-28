import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
public class JwtApiController {
    private final Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);

    @GetMapping("/api/values")
    public String getValuesFromJwt(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7); // remove "Bearer " prefix

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        String username = claims.get("username", String.class);
        String email = claims.get("email", String.class);

        Instant expirationInstant = claims.getExpiration().toInstant();
        LocalDateTime expirationTime = LocalDateTime.ofInstant(expirationInstant, ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return String.format("Username: %s, Email: %s, Expiration Time: %s",
                username, email, expirationTime.format(formatter));
    }
}
