package org.authetication.ecommerce.services;

import org.authetication.ecommerce.dto.request.UserLogin;
import org.authetication.ecommerce.dto.request.UserSignup;
import org.authetication.ecommerce.entity.roles.RolesEntity;
import org.authetication.ecommerce.entity.user.UserEntity;
import org.authetication.ecommerce.repository.RolesRepository;
import org.authetication.ecommerce.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;
    // RolesRepository rolesRepository;
    AuthenticationManager authManager;
    PasswordEncoder passwordEncoder;

    public record IssuedTokens(
            String accessToken,
            String refreshToken,
            String username) {
    }

    public UserService(UserRepository userRepository,
        //  RolesRepository rolesRepository, 
         AuthenticationManager authManager,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        // this.rolesRepository = rolesRepository;
        this.authManager = authManager;
        this.passwordEncoder = passwordEncoder;

    }

    public IssuedTokens regsiter(UserSignup req) {
        UserEntity userEntity = new UserEntity();
        // RolesEntity role = rolesRepository.findByRole("USER");
        // System.out.println(role.getRole());
        userEntity.setEmail(req.email());
        userEntity.setUsername(req.username());
        userEntity.setPassword(passwordEncoder.encode(req.password()));
        // userEntity.setRole(role);
        UserEntity savedUser = userRepository.save(userEntity);
        if (savedUser != null) {
            return issueTokens(savedUser.getUsername());
        }
        return null;
    }

    public IssuedTokens login(UserLogin req) {
        Authentication auth = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(req.username(), req.password()));
        if (auth.isAuthenticated()) {
            return issueTokens(req.username());
        }
        return null;
    }

    public IssuedTokens issueTokens(String username) {
        // You can add validation or transformation logic here if needed
        return new IssuedTokens("access_token", "refresh_token", username);
    }
}
