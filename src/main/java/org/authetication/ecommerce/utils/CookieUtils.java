package org.authetication.ecommerce.utils;


import jakarta.servlet.http.Cookie;
import org.authetication.ecommerce.dto.cookie.CookiePayload;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;
import java.util.Date;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CookieUtils {

    private final int expiration;

    public CookieUtils(@Value("${cookie.expiration}") int expiration) {
        this.expiration = expiration;
    }

    public Cookie setRefreshToken(CookiePayload o) {
        Cookie newCookie = new Cookie(o.cookieName(), o.cookieValue());
        newCookie.setMaxAge(expiration);

        if (o.cookieHttpOnly()) {
            newCookie.setHttpOnly(true);
        }

        return newCookie;
    }
}
