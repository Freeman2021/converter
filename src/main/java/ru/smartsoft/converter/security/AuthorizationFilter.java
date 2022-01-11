package ru.smartsoft.converter.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.smartsoft.converter.JWTUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token = request.getHeader("token");

        if (token != null) {
            if (JWTUtils.isTokenExpired(token)) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            } else {
                if (JWTUtils.isRefreshToken(token)) {
                    if (JWTUtils.getClaim(token, "login").equals(UserData.LOGIN) && JWTUtils.getClaim(token, "password").equals(UserData.PASSWORD)) {
                        String newToken = JWTUtils.generateToken(UserData.LOGIN, UserData.PASSWORD);
                        response.addHeader("token", newToken);
                    } else {
                        response.setStatus(HttpStatus.BAD_REQUEST.value());
                    }
                }
                String login = JWTUtils.getClaim(token, "login");
                if (login != null) {
                    final UsernamePasswordAuthenticationToken authentication
                            = new UsernamePasswordAuthenticationToken(login, null, new ArrayList<>());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
        filterChain.doFilter(request, response);
    }
}
