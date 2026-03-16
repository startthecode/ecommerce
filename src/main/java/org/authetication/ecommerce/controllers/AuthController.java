package org.authetication.ecommerce.controllers;

import org.authetication.ecommerce.dto.request.UserLogin;
import org.authetication.ecommerce.dto.request.UserSignup;
import org.authetication.ecommerce.dto.response.ApiBaseResponse;
import org.authetication.ecommerce.dto.response.AuthReponse;
import org.authetication.ecommerce.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    AuthController(AuthService authService) {
        this.authService = authService;
    }

    private static ApiBaseResponse<AuthReponse> toResponse(AuthService.IssuedTokens issuedTokens, boolean status,
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
    public ResponseEntity<ApiBaseResponse<AuthReponse>> login(@RequestBody UserLogin req) {
        AuthService.IssuedTokens issuedTokens = this.authService.login(req);
        return ResponseEntity.ok(toResponse(issuedTokens, true, "Login successful"));
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiBaseResponse<AuthReponse>> signup(@RequestBody UserSignup req) {
        AuthService.IssuedTokens issuedTokens = this.authService.register(req);
        return ResponseEntity.ok(toResponse(issuedTokens, true, "Login successful"));
    }

}
