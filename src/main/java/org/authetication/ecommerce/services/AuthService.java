package org.authetication.ecommerce.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.authetication.ecommerce.Mapping.AuthMapper;
import org.authetication.ecommerce.dto.request.UserLogin;
import org.authetication.ecommerce.dto.request.UserSignup;
import org.authetication.ecommerce.dto.request.auth.UserSignUpDto;
import org.authetication.ecommerce.entity.roles.RolesEntity;
import org.authetication.ecommerce.entity.user.UserEntity;
import org.authetication.ecommerce.exception.AuthException;
import org.authetication.ecommerce.exception.GenericException;
import org.authetication.ecommerce.repository.RolesRepository;
import org.authetication.ecommerce.repository.UserRepository;
import org.authetication.ecommerce.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    AuthMapper authmapper;

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
                       AuthMapper authmapper,
                       @Value("${cookie.refreshTokenName}") String cookieName
    ) {
        this.userRepository = userRepository;
         this.rolesRepository = rolesRepository;
        this.authManager = authManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.cookieName = cookieName;
        this.authmapper =  authmapper;

    }


    public IssuedTokens register(UserSignUpDto req) {
        if(userRepository.findByUsername(req.username()).isPresent()){
               throw new IllegalArgumentException("username Already Exists");
           }
        UserEntity entity = authmapper.toEntitySignup(req);
        entity.setPassword(passwordEncoder.encode(req.password()));
        entity.setRole(rolesRepository.findByRole("USER"));
        UserEntity newUser= userRepository.save(entity);
          return issueTokens(newUser.getUsername(),
                   jwtUtils.generateToken(
                           JwtUtils.TokenType.ACCESS,
                           req.username()),
                   jwtUtils.generateToken(
                           JwtUtils.TokenType.REFRESH,
                           req.username()));

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
