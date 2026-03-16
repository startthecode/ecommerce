package org.authetication.ecommerce.services;

import org.authetication.ecommerce.dto.request.UserLogin;
import org.authetication.ecommerce.dto.request.UserSignup;
import org.authetication.ecommerce.entity.roles.RolesEntity;
import org.authetication.ecommerce.entity.user.UserEntity;
import org.authetication.ecommerce.repository.RolesRepository;
import org.authetication.ecommerce.repository.UserRepository;
import org.authetication.ecommerce.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
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
                       JwtUtils jwtUtils
    ) {
        this.userRepository = userRepository;
         this.rolesRepository = rolesRepository;
        this.authManager = authManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;


    }

    public IssuedTokens register(UserSignup req) {
         UserEntity userEntity = new UserEntity();
         RolesEntity role = rolesRepository.findByRole("USER");
        userEntity.setEmail(req.email());
        userEntity.setUsername(req.username());
        userEntity.setName(req.name());
        userEntity.setPassword(passwordEncoder.encode(req.password()));
         userEntity.setRole(role);
        UserEntity savedUser = userRepository.save(userEntity);
        return issueTokens(savedUser.getUsername(), jwtUtils.generateToken(JwtUtils.TokenType.ACCESS,req.username()), jwtUtils.generateToken(JwtUtils.TokenType.REFRESH,req.username()));
    }

    public IssuedTokens login(UserLogin req) {
        Authentication auth = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(req.username(), req.password()));
        if (auth.isAuthenticated()) {
            return issueTokens(req.username(), jwtUtils.generateToken(JwtUtils.TokenType.ACCESS,req.username()), jwtUtils.generateToken(JwtUtils.TokenType.REFRESH,req.username()));
        }
        return null;
    }

    public IssuedTokens issueTokens(String username,String access_token,String refresh_token) {
        // You can add validation or transformation logic here if needed
        return new IssuedTokens(access_token, refresh_token, username);
    }
}
