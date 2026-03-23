package org.authetication.ecommerce.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.authetication.ecommerce.exception.AuthException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HexFormat;

@Component
public class JwtUtils{

    public static enum TokenType {
        ACCESS,
        REFRESH
    }
    private final long accessTimer;
    private final long refreshTimer;
    private final SecretKey accessSecret;
    private final SecretKey refreshSecret;

    public JwtUtils(
            @Value("${jwt.secret}") String accessHex,
            @Value("${jwt.expiration}") Long accessTimer,
            @Value("${jwt.refreshSecret}") String refreshHex,
            @Value("${jwt.refreshExpiration}") Long refreshTimer
    ){
        this.accessTimer = accessTimer;
        this.refreshTimer = refreshTimer;
        this.accessSecret = generateByte(accessHex);
        this.refreshSecret = generateByte(refreshHex);

    }


    public String generateToken(TokenType type,String username){
        SecretKey secretToken = type == TokenType.ACCESS ? this.accessSecret  : this.refreshSecret;
        long tokenExpi = type == TokenType.ACCESS ? this.accessTimer  : this.refreshTimer;
        return Jwts
                .builder()
                .signWith(secretToken)
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + tokenExpi))
                .issuedAt(new Date()).compact();
    }

    public String decodeToken(TokenType type,String token){
       try{
           Claims decodeToken = this.tokenParse(type,token);
           return decodeToken.getSubject();
       }catch (ExpiredJwtException e){
           throw AuthException.tokenExpired(type);
       }catch (Exception e){
           throw AuthException.invalidToken();
       }

    }

    private Claims tokenParse(TokenType type,String token) throws Exception{
        SecretKey tokenSecret = type == TokenType.ACCESS ? this.accessSecret  : this.refreshSecret;
      return Jwts.parser().verifyWith(tokenSecret).build().parseSignedClaims(token).getPayload();
    }


    static SecretKey generateByte(String hexKey){
        byte[] secret = HexFormat.of().parseHex(hexKey.trim());
        return Keys.hmacShaKeyFor(secret);
    }

}
