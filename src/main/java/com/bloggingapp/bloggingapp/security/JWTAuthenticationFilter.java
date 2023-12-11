package com.bloggingapp.bloggingapp.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFilter;
public class JWTAuthenticationFilter extends AuthenticationFilter {

    private final JWTAuthenticationManager jwtAuthenticationManager;

    public JWTAuthenticationFilter(JWTAuthenticationManager jwtAuthenticationManager) {
        super(jwtAuthenticationManager, new JWTAuthenticationConverter());
        System.out.println("JWTAuthenticationFilter bean created");
        this.jwtAuthenticationManager = jwtAuthenticationManager;

        this.setSuccessHandler((request, response, authentication) ->
                SecurityContextHolder.getContext().setAuthentication(authentication)
        );
    }
}
