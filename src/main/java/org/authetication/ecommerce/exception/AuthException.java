package org.authetication.ecommerce.exception;

import org.authetication.ecommerce.utils.JwtUtils;

public class AuthException extends RuntimeException {

    private final String code;
    private final boolean status;

    public AuthException(String message, String code, boolean status) {
        super(message);
        this.code = code;
        this.status = status;
    }

    public String getCode() {
        return this.code;
    }

    public boolean getStatus() {
        return this.status;
    }

    // =========================
    // 🔐 TOKEN RELATED
    // =========================

    public static AuthException tokenExpired(JwtUtils.TokenType type) {
        String message = type == JwtUtils.TokenType.REFRESH
                ? "Refresh token is expired"
                : "Access token is expired";

        return new AuthException(message, "TOKEN_EXPIRED", false);
    }

    public static AuthException invalidToken() {
        return new AuthException("Invalid token", "INVALID_TOKEN", false);
    }

    public static AuthException tokenMissing() {
        return new AuthException("Token is missing", "TOKEN_MISSING", false);
    }

    public static AuthException tokenMalformed() {
        return new AuthException("Malformed token", "TOKEN_MALFORMED", false);
    }

    // =========================
    // 👤 USER RELATED
    // =========================

    public static AuthException userNotFound() {
        return new AuthException("User not found", "USER_NOT_FOUND", false);
    }

    public static AuthException userDisabled() {
        return new AuthException("User account is disabled", "USER_DISABLED", false);
    }

    public static AuthException invalidCredentials() {
        return new AuthException("Invalid email or password", "INVALID_CREDENTIALS", false);
    }

    // =========================
    // 🔑 AUTHORIZATION
    // =========================

    public static AuthException accessDenied() {
        return new AuthException("Access denied", "ACCESS_DENIED", false);
    }

    public static AuthException insufficientPermissions() {
        return new AuthException("Insufficient permissions", "FORBIDDEN", false);
    }

    // =========================
    // 🔄 SESSION / FLOW
    // =========================

    public static AuthException sessionExpired() {
        return new AuthException("Session expired. Please login again", "SESSION_EXPIRED", true);
    }

    public static AuthException refreshTokenInvalid() {
        return new AuthException("Invalid refresh token", "REFRESH_INVALID", false);
    }

    public static AuthException logoutFailed() {
        return new AuthException("Logout failed", "LOGOUT_FAILED", true);
    }
}