package org.authetication.ecommerce.dto.cookie;

public record CookiePayload(
        String cookieName,
        String cookieValue,
        boolean cookieHttpOnly
) {}
