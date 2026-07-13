package com.twilight.configurations;

import com.twilight.security.filters.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/auth/**",
                                "/auth"
                        ).permitAll()

                        .requestMatchers(
                                "/account/**",
                                "/account"
                        ).hasRole("verified")

                        // Merchant APIs
                        .requestMatchers(
                                "/merchant/**",
                                "/merchant"
                        ).hasRole("merchant")
                        .requestMatchers(
                                "/manager/**",
                        "/manager"
                        ).hasRole("manager")
                        .requestMatchers(
                                "/customer/**",
                                "/customer",
                                "/search",
                                "/search/**",
                                "/images/**",
                                "/images"
                        ).hasAllRoles("customer","merchant","manager")

                        .anyRequest().authenticated()
                )

                .httpBasic(AbstractHttpConfigurer::disable)

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

}
