package com.bonusver.task.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    public static final String ADMIN = "admin";
    public static final String USER = "user";

    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                {
                    //authorizeRequests.requestMatchers(HttpMethod.GET, "/api/v1/tasks").hasAnyRole(ADMIN, USER);
                    //authorizeRequests.requestMatchers(HttpMethod.POST, "/api/v1/tasks").hasRole(ADMIN);
                    //authorizeRequests.requestMatchers(HttpMethod.PATCH, "/api/v1/tasks/{taskId}").hasAnyRole(ADMIN, USER);
                    //authorizeRequests.requestMatchers(HttpMethod.DELETE, "/api/v1/tasks/{taskId}").hasRole(ADMIN);
                    authorizeRequests.anyRequest().authenticated();
                });

        http
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(
                        jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)
                ));

        http
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
