package com.duoc.splim.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuracion central de Spring Security.
 * 
 * Define:
 * -que rutas son publicas y cuales requieren autentificacion
 * -que rol puede acceder a cada tipo de operacion
 * - session stateless (sin cookies de session, solo JWT)
 * -registro del jwtfilter en la cadena de filtros
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            //Desabilita CSRF (no es necesario en APIs REST stateless con JWT)
            .csrf(csrf -> csrf.disable())

            //Sin sessiones en servidor: cada request se autentifica solo con JWT
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            //reglas de autorizacion por ruta y metodo http
            .authorizeHttpRequests(auth -> auth
                
                //endpoints de autentificacion: publicos (no requieren token)
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers(
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**"
                ).permitAll()

                                // Swagger UI y OpenAPI docs: públicos
                .requestMatchers(
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**"
                ).permitAll()

                // Swagger UI y OpenAPI docs: públicos
                .requestMatchers(
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**"
                ).permitAll()

                // Swagger UI y OpenAPI docs: públicos
                .requestMatchers(
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**"
                ).permitAll()

                // Swagger UI y OpenAPI docs: públicos
                .requestMatchers(
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**"
                ).permitAll()

                // Swagger UI y OpenAPI docs: públicos
                .requestMatchers(
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**"
                ).permitAll()

                // Swagger UI y OpenAPI docs: públicos
                .requestMatchers(
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**"
                ).permitAll()

                //lectura (get): cualquier usuario autentificado (User o Admin)
                .requestMatchers(HttpMethod.GET, "/api/v1/**").hasAnyRole("USER", "ADMIN")

                // Escritura (POST, PUT ,DELETE): solo admin
                .requestMatchers(HttpMethod.POST, "/api/v1/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/v1/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/**").hasAnyRole("ADMIN")

                //cualquier otra ruta necesita autentificacion
                .anyRequest().authenticated()
            )

            // agregar el filtro JWT antes del filtro de autentuficacion por usuario/contraseña
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * BCrypt para encriptar contraseñas. Factor de costo 10 ( por defecto )
     * Nunca se guarda la contraseña en texto plano en la BD
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * expone el authentificationManager como bean para usarlo en AuthController
     * Spring lo configura automaticamente usando UserDetailsServiceImpl y PasswordEncoder
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws  Exception {
        return config.getAuthenticationManager();
    }
}
