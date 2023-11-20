package Control.Cia.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JwtUtil {
    private String secret="Hola";

    public Date extractExpiration(String token){
        return  extratClaims(token,Claims::getExpiration);
    }
    public String extracUsername(String token){
        return  extratClaims(token,Claims::getSubject);
    }
    public <T> T extratClaims(String token , Function<Claims,T> claimsResolver){
        final Claims claims =extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public  Claims extractAllClaims(String token){
        JwtParser parser = Jwts.parser()
                .setSigningKey(secret)
                .build();
        Claims claims = parser.parseClaimsJws(token).getBody();


        return claims;
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username,String role){
        Map<String,String> claims = new HashMap<>();
        claims.put("role",role);
        return createToken(claims,username);
    }

    private String createToken(Map<String,String> claims ,String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+100 * 60 * 60 *10))
                .signWith(SignatureAlgorithm.HS256,secret).compact();
    }

    public boolean validateToken(String token , UserDetails userdetails){
        final  String username=extracUsername(token);
        return (username.equals(userdetails.getUsername()) && !isTokenExpired(token));
    }

}
