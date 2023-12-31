package Control.Cia.Security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {
    /**
     * Clave secreta utilizada para firmar y verificar tokens JWT con el algoritmo HS256.
     * Se genera de forma aleatoria al iniciar la clase.
     */
    private Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * Extrae la fecha de expiración de un token JWT.
     *
     * @param token El token JWT del cual se extraerá la fecha de expiración.
     * @return La fecha de expiración del token.
     */
    public Date extractExpiration(String token) {
        // Utilizar el método extratClaims con un resolvedor de reclamaciones que obtenga la fecha de expiración
        return extratClaims(token, Claims::getExpiration);
    }

    /**
     * Extrae el nombre de usuario de un token JWT.
     *
     * @param token El token JWT del cual se extraerá el nombre de usuario.
     * @return El nombre de usuario del token.
     */
    public String extracUsername(String token) {
        // Utilizar el método extratClaims con un resolvedor de reclamaciones que obtenga el nombre de usuario
        return extratClaims(token, Claims::getSubject);
    }

    /**
     * Extrae reclamaciones específicas de un token JWT utilizando un resolvedor de reclamaciones.
     *
     * @param token El token JWT del cual se extraerán las reclamaciones.
     * @param claimsResolver El resolvedor de reclamaciones que define qué reclamación extraer.
     * @return El resultado de aplicar el resolvedor de reclamaciones a las reclamaciones del token.
     * @param <T> El tipo de dato del resultado esperado.
     */
    public <T> T extratClaims(String token, Function<Claims, T> claimsResolver) {
        // Extraer todas las reclamaciones (claims) del token
        final Claims claims = extractAllClaims(token);

        // Aplicar el resolvedor de reclamaciones para obtener un resultado específico (puede ser la fecha de expiración o el nombre de usuario, entre otros)
        return claimsResolver.apply(claims);
    }

    /**
     * Extrae todas las reclamaciones (claims) de un token JWT.
     *
     * @param token El token JWT del cual se extraerán las reclamaciones.
     * @return Un objeto Claims que contiene todas las reclamaciones del token.
     */
    public Claims extractAllClaims(String token) {
        // Crear un parser JWT configurado con la clave secreta para validar y desencriptar el token
        JwtParser parser = Jwts.parser()
                .setSigningKey(secretKey)
                .build();

        // Parsear el token y extraer las reclamaciones (claims) del cuerpo del token
        Claims claims = parser.parseClaimsJws(token).getBody();

        // Devolver el objeto Claims que contiene todas las reclamaciones del token
        return claims;
    }


    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    /**
     * Genera un token JWT para el usuario con el nombre de usuario y el rol proporcionados.
     *
     * @param username El nombre de usuario para el cual se generará el token.
     * @param role El rol asociado al usuario para incluir en el token.
     * @return El token JWT generado para el usuario con las reclamaciones y el sujeto especificados.
     */
    public String generateToken(String username, String role) {
        // Crear un mapa de reclamaciones (claims) con el rol del usuario
        Map<String, String> claims = new HashMap<>();
        claims.put("role", role);

        // Utilizar el método createToken para generar el token JWT con las reclamaciones y el nombre de usuario
        return createToken(claims, username);
    }


    /**
     * Crea un token JWT con las reclamaciones especificadas y el sujeto proporcionado.
     *
     * @param claims Un mapa de reclamaciones (claims) que se incluirán en el token.
     * @param subject El sujeto al cual se asociará el token.
     * @return El token JWT creado con las reclamaciones y el sujeto especificados.
     */
    private String createToken(Map<String, String> claims, String subject) {
        // Construir el token JWT utilizando la biblioteca Jwts
        return Jwts.builder()
                // Establecer las reclamaciones en el token
                .setClaims(claims)
                // Establecer el sujeto del token
                .setSubject(subject)
                // Establecer la fecha de emisión del token (actual)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // Establecer la fecha de expiración del token (10 horas después de la emisión)
                .setExpiration(new Date(System.currentTimeMillis() + 100 * 60 * 60 * 10))
                // Firmar el token con el algoritmo de firma y la clave secreta proporcionada
                .signWith(SignatureAlgorithm.HS256, secretKey)
                // Compactar el token en una cadena
                .compact();
    }


    /**
     * Método para validar un token JWT.
     *
     * @param token El token JWT que se va a validar.
     * @param userdetails Los detalles del usuario contra los cuales se validará el token.
     * @return true si el token es válido y no ha caducado, false de lo contrario.
     */
    public boolean validateToken(String token, UserDetails userdetails) {
        // Extraer el nombre de usuario del token
        final String username = extracUsername(token);

        // Verificar si el nombre de usuario en el token coincide con el nombre de usuario en los detalles del usuario
        // y asegurarse de que el token no esté caducado
        return (username.equals(userdetails.getUsername()) && !isTokenExpired(token));
    }

}
