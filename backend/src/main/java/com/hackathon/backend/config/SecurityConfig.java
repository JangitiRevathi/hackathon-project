package com.hackathon.backend.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. Disable CSRF (essential for testing POST requests like placing orders)
                .csrf(csrf -> csrf.disable())

                // 2. Configure CORS so your Frontend can talk to the Backend
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of("*"));
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    config.setAllowedHeaders(List.of("*"));
                    return config;
                }))

                // 3. Set up the "Bouncer" rules
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/**",            // Allows your Medicine and Order APIs
                                "/swagger-ui/**",     // Allows the Swagger Web Page
                                "/v3/api-docs/**",    // Allows the underlying JSON docs
                                "/swagger-ui.html"
                        ).permitAll()             // No login required for these!
                        .anyRequest().permitAll() // Everything else needs a login
                );

        return http.build();
    }
}