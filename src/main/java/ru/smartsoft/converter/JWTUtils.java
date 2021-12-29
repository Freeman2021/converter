package ru.smartsoft.converter;

import com.auth0.jwt.JWT;

import java.time.Instant;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public final class JWTUtils {

    private final static long TIME = 3_600_000;
    private final static String SECRET = "HELP_ME_PLEASE";

    public static String generateToken(String login, String password) {
        return JWT.create()
                .withClaim("login", login)
                .withClaim("password", password)
                .withClaim("refresh-token", Date.from(Instant.now().plusMillis(TIME/2)))
                .withExpiresAt(Date.from(Instant.now().plusMillis(TIME)))
                .sign(HMAC512(SECRET.getBytes()));
    }

    public static String getClaim(String token, String claim) {
        return JWT.decode(token).getClaim(claim).asString();
    }

    public static boolean isRefreshToken(String token) {
        return new Date().after(JWT.decode(token).getClaim("refresh-token").asDate());
    }

    public static Date getExpirationDate(String token) {
        return JWT.decode(token).getExpiresAt();
    }

    public static boolean isTokenExpired(String token) {
        return new Date().after(getExpirationDate(token));
    }
}
