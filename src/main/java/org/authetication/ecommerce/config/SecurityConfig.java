package org.authetication.ecommerce.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Null;
import org.authetication.ecommerce.dto.response.ApiBaseResponse;
import org.authetication.ecommerce.security.JwtFilterChain;
import org.authetication.ecommerce.services.imp.UserDetailsImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
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
    private final ObjectMapper mapper;

    public SecurityConfig(UserDetailsImp userDetailsImp,JwtFilterChain jwtFilterChain,ObjectMapper mapper) {
        this.UserDetailsImp = userDetailsImp;
        this.jwtFilterChain = jwtFilterChain;
        this.mapper = mapper;


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
                .addFilterBefore(jwtFilterChain, UsernamePasswordAuthenticationFilter.class).exceptionHandling(
                        ex->ex.authenticationEntryPoint((HttpServletRequestreq,  res,authException)->
                                {
                                    res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                    res.setContentType(MediaType.APPLICATION_JSON_VALUE);
                                    mapper.writeValue(res.getWriter(),new ApiBaseResponse<Null>(false,"Something went wrong",null));

                                }
                                )
                );
            return http.build();
    }


}
