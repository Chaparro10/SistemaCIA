package Control.Cia.Security.jwt;

import Control.Cia.Security.UserDetailService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component

public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtutil;

    @Autowired
    private UserDetailService userDetailService;

    Claims claims = null;
    private String username=null;

    /**
     * Implementación principal del filtro para manejar la autenticación JWT en cada solicitud.
     *
     * @param request La solicitud HTTP que está siendo procesada.
     * @param response La respuesta HTTP que se enviará al cliente.
     * @param filterChain Cadena de filtros para continuar con la ejecución del siguiente filtro.
     * @throws ServletException Excepción de servlet en caso de un error durante el procesamiento de la solicitud.
     * @throws IOException Excepción de entrada/salida en caso de un error durante la lectura o escritura de datos.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Excluir ciertos endpoints de la autenticación JWT
        if(request.getServletPath().matches("(?i)/cia-app/Login|/user/forgotPassword|/user/signup")){
            filterChain.doFilter(request, response);
        } else {
            String authorizationHeader = request.getHeader("Authorization");
            String token = null;

            // Extraer y validar el token de autorización
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                token = authorizationHeader.substring(7);
                username = jwtutil.extracUsername(token);
                claims = jwtutil.extractAllClaims(token);
            }

            // Autenticar al usuario si el token es válido
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailService.loadUserByUsername(username);
                if (jwtutil.validateToken(token, userDetails)) {
                    // Crear la autenticación y establecerla en el contexto de seguridad
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    new WebAuthenticationDetailsSource().buildDetails(request);

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                } else {
                    // Manejar el caso en que el token no es válido
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token no válido");
                    return;
                }
            } else {
                // Manejar el caso en que el usuario no está autenticado
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuario no autenticado");
                return;
            }

            // Continuar con la cadena de filtros
            filterChain.doFilter(request, response);
        }
    }

    /**
     * Verifica si el usuario tiene el rol de administrador.
     *
     * @return true si el usuario es administrador, false de lo contrario.
     */
    public Boolean isAdmin(){
        return "admin".equalsIgnoreCase((String) claims.get("role"));
    }
    /**
     * Verifica si el usuario tiene el rol de usuario normal.
     *
     * @return true si el usuario es un usuario normal, false de lo contrario.
     */
    public Boolean isUser(){
        return "user".equalsIgnoreCase((String) claims.get("role"));
    }
    /**
     * Obtiene el nombre de usuario actual del token.
     *
     * @return El nombre de usuario actual.
     */
    public String getCurrentUser(){
        return  username;
    }
}


