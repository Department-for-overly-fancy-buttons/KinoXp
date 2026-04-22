package com.example.kinoxp.security;

import com.example.kinoxp.user.JpaUserDetailsService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http
                .csrf(CsrfConfigurer::spa)
                .authorizeHttpRequests(auth -> auth
                        //.requestMatchers("/").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/reservations").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/showings").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/users/log_in").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users/user").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/showings/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/theaters/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/showings").hasAuthority("ROLE_EMPLOYEE")
                        .requestMatchers(HttpMethod.POST,"/api/movies").hasAuthority("ROLE_EMPLOYEE")
                        .anyRequest().hasAuthority("ROLE_ADMIN")
                )
                .formLogin(form -> form
                        .loginProcessingUrl("/api/users/log_in")
                        .successHandler((_, res, _) -> res.setStatus(HttpServletResponse.SC_NO_CONTENT))
                        .failureHandler((req, res, ex) -> res.setStatus(HttpServletResponse.SC_UNAUTHORIZED))

                )
                .logout(logout -> logout
                        .logoutUrl("/api/logout") // Endpoint for logout requests
                        .logoutSuccessHandler((req, res, auth) -> res.setStatus(HttpServletResponse.SC_NO_CONTENT)) // 204 on success
                )
                // Return 401 instead of redirecting to /login
                .exceptionHandling(eh -> eh
                        .authenticationEntryPoint((req, res, ex) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                );
        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
