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
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/", "/noticia/**", "/noticias-do-reino", "/economia-e-comercio", "/vida-na-corte", "/cronicas-e-opinioes", "/arte-e-cultura", "/casas/**", "/arquivos/**", "/usuarios/registrar", "/usuarios/login", "/stylesheets/**", "/scripts/**", "/images/**", "/fonts/**", "/h2-console/**").permitAll()
                                .requestMatchers("/noticias/cadastrar", "/noticias/editar/**", "/noticias/excluir/**", "/noticias/listar").authenticated()
                                .anyRequest().permitAll()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/usuarios/login")
                                .loginProcessingUrl("/usuarios/login")
                                .defaultSuccessUrl("/noticias/listar", true)
                                .failureUrl("/usuarios/login?error=true")
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/usuarios/login?logout=true")
                                .permitAll()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
