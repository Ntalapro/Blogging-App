package com.bloggingapp.bloggingapp.security;

import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;

import javax.servlet.http.HttpServletRequest;

public class JWTAuthenticationFilter extends AuthenticationFilter {
    public JWTAuthenticationFilter() {
        super(new JWTAuthenticationManager(), new JWTAunthenticationConveter());
    }
}
