package com.bloggingapp.bloggingapp.security;

import com.bloggingapp.bloggingapp.users.UserEntity;
import com.bloggingapp.bloggingapp.users.UsersService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
public class JWTAuthenticationManager implements AuthenticationManager {

    private final JWTService jwtService;
    private final UsersService usersService;

    public JWTAuthenticationManager(JWTService jwtService, UsersService usersService) {
        this.jwtService = jwtService;
        this.usersService = usersService;
        System.out.println("JWTAuthenticationManager bean created");
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof JWTAuthentication) {
            // Authenticate User
            JWTAuthentication jwtAuthentication = (JWTAuthentication) authentication;
            String jwt = jwtAuthentication.getCredentials();
            Long userId = jwtService.retrieveUserId(jwt);
            UserEntity userEntity = usersService.getUser(userId);

            jwtAuthentication.setUserEntity(userEntity);
            jwtAuthentication.setAuthenticated(true);

            return jwtAuthentication;
        }

        throw new IllegalArgumentException("Cannot authenticate with non-JWT authentication");
    }
}
