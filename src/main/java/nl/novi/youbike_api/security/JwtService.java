package nl.novi.youbike_api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import nl.novi.youbike_api.dto.UserLoginResponseDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
        private final static String SECRET_KEY = "lgljhasfdglahblijghblkjlsdfgsdkjhfgkjisdhfijjasfjasdfjasdfjasdjsdah";

        private Key getSigningKey() {
            byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
            return Keys.hmacShaKeyFor(keyBytes);
        }
        public String extractUsername(String token) {
            return extractClaim(token, Claims::getSubject);
        }

        private Date extractExpiration(String token) {
            return extractClaim(token, Claims::getExpiration);
        }

        private <T> T extractClaim(String token, Function<Claims, T>
                claimsResolver) {
            final Claims claims = extractAllClaims(token);
            return claimsResolver.apply(claims);
        }

        private Claims extractAllClaims(String token) {
            return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
        }

        private Boolean isTokenExpired(String token) {
            return extractExpiration(token).before(new Date());
        }

        public String generateToken(UserDetails userDetails, UserLoginResponseDTO dto) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("role", dto.getRole().name());
            if (dto.getCyclistId() != null) {
                claims.put("cyclist_id", dto.getCyclistId());
            }
            if (dto.getBikeCompanyId() != null) {
                claims.put("bike_company_id", dto.getBikeCompanyId());
            }
            return createToken(claims, userDetails.getUsername());
        }

        private String createToken(Map<String, Object> claims, String
                subject) {
            long validPeriod = 1000L * 60 * 60 * 24 * 8; // 8 days in ms
            long currentTime = System.currentTimeMillis();
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(subject)
                    .setIssuedAt(new Date(currentTime))
                    .setExpiration(new Date(currentTime + validPeriod))
                    .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                    .compact();
        }

        public Boolean validateToken(String token, UserDetails
                userDetails) {
            final String username = extractUsername(token);
            return username.equals(userDetails.getUsername()) &&
                    !isTokenExpired(token);
        }
}
