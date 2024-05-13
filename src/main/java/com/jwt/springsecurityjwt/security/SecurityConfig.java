package com.jwt.springsecurityjwt.security;

import com.jwt.springsecurityjwt.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsImpl userDetailsImpl;
    @Autowired
    private JitAuthenticationFilter jitAuthenticationFilter;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req->req.requestMatchers("/login/**", "/register/**")
                                .permitAll()
                                .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                                .anyRequest()
                                .authenticated()
                ).userDetailsService(userDetailsImpl)
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jitAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}

/**
 * @GetMapping("/{id}")
public ResponseEntity<User> getUserById(@PathVariable Long id) {
String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
User loggedInUser = userService.getUserByUsername(loggedInUsername);
Optional<User> user = userService.getUserById(id);
if (user.isPresent()) {
if (loggedInUser.getRole() == UserRole.ADMIN || user.get().getUsername().equals(loggedInUsername)) {
return ResponseEntity.ok(user.get());
} else {
return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
}
} else {
return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
}
}
 */