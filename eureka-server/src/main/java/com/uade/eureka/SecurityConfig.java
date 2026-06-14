package com.uade.eureka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Good, this fixes registration issues
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/eureka/**", "/actuator/**").permitAll() // Added "/"
                        .anyRequest().authenticated())
                .httpBasic(org.springframework.security.config.Customizer.withDefaults()); // Keeps basic auth active
                                                                                           // for other paths

        return http.build();
    }
}
