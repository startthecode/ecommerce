package org.authetication.ecommerce.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Null;
import org.authetication.ecommerce.dto.cookie.CookiePayload;
import org.authetication.ecommerce.dto.request.UserLogin;
import org.authetication.ecommerce.dto.request.UserSignup;
import org.authetication.ecommerce.entity.roles.RolesEntity;
import org.authetication.ecommerce.entity.user.UserEntity;
import org.authetication.ecommerce.exception.AuthException;
import org.authetication.ecommerce.exception.GenericException;
import org.authetication.ecommerce.repository.RolesRepository;
import org.authetication.ecommerce.repository.UserRepository;
import org.authetication.ecommerce.utils.CookieUtils;
import org.authetication.ecommerce.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AuthService{
    String cookieName;
    UserRepository userRepository;
     RolesRepository rolesRepository;
    AuthenticationManager authManager;
    PasswordEncoder passwordEncoder;
    JwtUtils jwtUtils;

    public record IssuedTokens(
            String accessToken,
            String refreshToken,
            String username) {
    }

    public AuthService(UserRepository userRepository,
                       RolesRepository rolesRepository,
                       AuthenticationManager authManager,
                       PasswordEncoder passwordEncoder,
                       JwtUtils jwtUtils,
                       @Value("${cookie.refreshTokenName}") String cookieName
    ) {
        this.userRepository = userRepository;
         this.rolesRepository = rolesRepository;
        this.authManager = authManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.cookieName = cookieName;


    }

    public IssuedTokens register(UserSignup req) {
       try{
           if(userRepository.findByUsername(req.username()).isPresent()){
               throw new IllegalArgumentException("username Already Exists");
           }
           UserEntity userEntity = new UserEntity();
           RolesEntity role = rolesRepository.findByRole("USER");
           userEntity.setEmail(req.email());
           userEntity.setUsername(req.username());
           userEntity.setName(req.name());
           userEntity.setPassword(passwordEncoder.encode(req.password()));
           userEntity.setRole(role);
           UserEntity savedUser = userRepository.save(userEntity);
           return issueTokens(savedUser.getUsername(),
                   jwtUtils.generateToken(
                           JwtUtils.TokenType.ACCESS,
                           req.username()),
                   jwtUtils.generateToken(
                           JwtUtils.TokenType.REFRESH,
                           req.username()));
       }catch (Exception e){
           throw new GenericException("unable to create user at this moment");
       }
    }

    public IssuedTokens login(UserLogin req) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.username(), req.password())
        );

        return issueTokens(
                req.username(),
                jwtUtils.generateToken(JwtUtils.TokenType.ACCESS, req.username()),
                jwtUtils.generateToken(JwtUtils.TokenType.REFRESH, req.username())
        );
    }

    public IssuedTokens refreshToken(HttpServletRequest req){
        Cookie[] allCookies = req.getCookies();
        String token = null;
        if (allCookies != null) {
            token = Arrays.stream(allCookies)
                    .filter(c -> c.getName().equals(this.cookieName))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);
        }
        System.out.println(token);
        if(token == null) throw AuthException.tokenMissing();
        String username = jwtUtils.decodeToken(JwtUtils.TokenType.REFRESH,token);
        return issueTokens(
                username,
                jwtUtils.generateToken(JwtUtils.TokenType.ACCESS, username),
                jwtUtils.generateToken(JwtUtils.TokenType.REFRESH, username)
        );
    }

    public IssuedTokens issueTokens(String username,String access_token,String refresh_token) {
        // You can add validation or transformation logic here if needed
        return new IssuedTokens(access_token, refresh_token, username);
    }


}
