package com.bloggingapp.bloggingapp.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

import javax.servlet.http.HttpServletRequest;

public class JWTAunthenticationConveter implements AuthenticationConverter {
    @Override
    public Authentication convert(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }

        String jwt = authHeader.substring(7);

        return new JWTAuthentication(jwt);
    }
}
