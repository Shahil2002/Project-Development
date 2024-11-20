package com.shahil.Air_Bnb.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http

    ) throws Exception {
        //hcd2
        http.csrf().disable().cors().disable();
        //haap
        http.authorizeHttpRequests().anyRequest().permitAll();
        return http.build();



    }
}
