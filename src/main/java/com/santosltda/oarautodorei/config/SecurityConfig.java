package com.santosltda.oarautodorei.config;

import com.santosltda.oarautodorei.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/", "/usuarios/registrar", "/usuarios/login", "/stylesheets/**", "/scripts/**", "/images/**", "/fonts/**", "/static/**").permitAll()
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/noticias/**").authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/usuarios/login")
                                .loginProcessingUrl("/usuarios/login") // Ensure this matches the form action URL
                                .defaultSuccessUrl("/noticias/listar", true)
                                .failureUrl("/usuarios/login?error=true")
                                .permitAll()
                )
                .logout(logout -> logout.permitAll());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
