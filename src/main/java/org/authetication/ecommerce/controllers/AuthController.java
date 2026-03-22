package org.authetication.ecommerce.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.authetication.ecommerce.Mapping.ResponseMapper;
import org.authetication.ecommerce.dto.request.UserLogin;
import org.authetication.ecommerce.dto.request.UserSignup;
import org.authetication.ecommerce.dto.response.ApiBaseResponse;
import org.authetication.ecommerce.dto.response.AuthReponse;
import org.authetication.ecommerce.services.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private String cookieName;
    private Long cookieExpiry;

    AuthController(AuthService authService, @Value("${cookie.refreshTokenName}") String cookieName,
            @Value("${cookie.expiration}") Long cookieExpiry) {
        this.authService = authService;
        this.cookieName = cookieName;
        this.cookieExpiry = cookieExpiry;

    }

    private static ApiBaseResponse<AuthReponse> toResponse(AuthService.IssuedTokens issuedTokens, boolean status,
            String message) {
        return ResponseMapper.success(
                new AuthReponse(
                        issuedTokens.accessToken(),
                        issuedTokens.refreshToken(),
                        issuedTokens.username()),
                message
                );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiBaseResponse<AuthReponse>> login(@RequestBody UserLogin req, HttpServletResponse res) {
        AuthService.IssuedTokens issuedTokens = this.authService.login(req);
        this.addResponseCookie(res, issuedTokens);
        return ResponseEntity.ok(toResponse(issuedTokens, true, "Login successful"));
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiBaseResponse<AuthReponse>> signup(@RequestBody UserSignup req, HttpServletResponse res) {
        AuthService.IssuedTokens issuedTokens = this.authService.register(req);
        this.addResponseCookie(res, issuedTokens);
        return ResponseEntity.ok(toResponse(issuedTokens, true, "Login successful"));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiBaseResponse<AuthReponse>> refresh(HttpServletRequest req, HttpServletResponse res) {
        AuthService.IssuedTokens issuedTokens = this.authService.refreshToken(req);
        this.addResponseCookie(res, issuedTokens);
        return ResponseEntity.ok(toResponse(issuedTokens, true, "Refreshed successful"));
    }

    private void addResponseCookie(HttpServletResponse resp, AuthService.IssuedTokens i) {
        ResponseCookie cookie = ResponseCookie.from(this.cookieName, i.refreshToken()).maxAge(this.cookieExpiry)
                .httpOnly(true).build();
        resp.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}
