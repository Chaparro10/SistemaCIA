package Control.Cia.Security;

import Control.Cia.Security.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder(16, 32, 8, 32, 4);
    }



    /**
     * Configuración de seguridad para la aplicación, estableciendo una cadena de filtros de seguridad personalizada.
     * Define reglas específicas para ciertas rutas, configura la autorización para las solicitudes HTTP,
     * utiliza autenticación básica de HTTP y agrega un filtro JWT personalizado antes del filtro estándar de autenticación por nombre de usuario y contraseña.
     *
     * @param http Configuración de seguridad de HTTP proporcionada por Spring Security.
     * @return SecurityFilterChain que representa la cadena de filtros de seguridad configurada.
     * @throws Exception Si hay problemas al configurar la seguridad.
     */
    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http
                // Establece un matcher de seguridad para rutas específicas.(publicas)
                .securityMatcher("/user/login", "user/signup", "/user/forgotPassword")

                // Configura la autorización para las solicitudes HTTP.
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )

                // Configura la autenticación básica de HTTP.
                .httpBasic(Customizer.withDefaults());

        // Agrega un filtro personalizado (jwtFilter) antes del filtro de autenticación por nombre de usuario y contraseña.
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        // Devuelve la configuración de la cadena de filtros de seguridad construida.
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }


}
