package org.authetication.ecommerce.controllers;

import org.authetication.ecommerce.dto.request.UserLogin;
import org.authetication.ecommerce.dto.request.UserSignup;
import org.authetication.ecommerce.dto.response.ApiBaseResponse;
import org.authetication.ecommerce.dto.response.AuthReponse;
import org.authetication.ecommerce.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    AuthController(UserService userService) {
        this.userService = userService;
    }

    private static ApiBaseResponse<AuthReponse> toResponse(UserService.IssuedTokens issuedTokens, boolean status,
            String message) {
        return new ApiBaseResponse<>(
                status,
                message,
                new AuthReponse(
                        issuedTokens.accessToken(),
                        issuedTokens.refreshToken(),
                        issuedTokens.username()));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiBaseResponse<AuthReponse>> login(UserLogin req) {
        UserService.IssuedTokens issuedTokens = this.userService.login(req);
        return ResponseEntity.ok(toResponse(issuedTokens, true, "Login successful"));
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiBaseResponse<AuthReponse>> signup(UserSignup req) {
        UserService.IssuedTokens issuedTokens = this.userService.regsiter(req);
        return ResponseEntity.ok(toResponse(issuedTokens, true, "Login successful"));
    }

}
