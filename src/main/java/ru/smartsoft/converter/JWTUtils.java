package ru.smartsoft.converter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Clock;

import java.util.Date;
import java.util.List;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public final class JWTUtils {

    private final static Clock clock = Date::new;
    private final static int TIME = 60000;
    private final static String SECRET = "rgyivrenvruoenboge";

    public static String generateToken(String login, String password) {
        final Date createDate = clock.getToday();
        return JWT.create()
                .withAudience(
                        login,
                        password
                )
                .withExpiresAt(new Date(createDate.getTime() + TIME))
                .sign(HMAC512(SECRET.getBytes()));
    }

    public static List<String> getAudience(String token) {
        return JWT.decode(token).getAudience();
    }

    public static Date getExpirationDate(String token) {
        return JWT.decode(token).getExpiresAt();
    }

    public static boolean isTokenExpired(String token) {
        return clock.getToday().after(getExpirationDate(token));
    }
}
