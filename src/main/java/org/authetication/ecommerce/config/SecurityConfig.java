package org.authetication.ecommerce.config;

import jakarta.servlet.FilterChain;
import org.authetication.ecommerce.security.JwtFilterChain;
import org.authetication.ecommerce.services.imp.UserDetailsImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final UserDetailsImp UserDetailsImp;
    private final JwtFilterChain jwtFilterChain;

    public SecurityConfig(UserDetailsImp userDetailsImp,JwtFilterChain jwtFilterChain) {
        this.UserDetailsImp = userDetailsImp;
        this.jwtFilterChain = jwtFilterChain;

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration cfg)
            throws Exception {
        return cfg.getAuthenticationManager();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(UserDetailsImp);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;   
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth->auth.requestMatchers("/api/auth/**","/api/testing/unprotected").permitAll()
                .anyRequest().authenticated())
                .sessionManagement(sesssion->sesssion.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFilterChain, UsernamePasswordAuthenticationFilter.class);
            return http.build();
    }


}
