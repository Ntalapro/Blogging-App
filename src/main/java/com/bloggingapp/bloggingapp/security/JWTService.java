package com.bloggingapp.bloggingapp.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import lombok.var;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    // TODO: Move the key to a seperate .properties file (not in git!)
    private static final String JWT_KEY = "lkhqx9553wctnqw4ytlnc5sag3wv787654";

    private Algorithm algorithm = Algorithm.HMAC256(JWT_KEY);


    public String createJwt(Long userID){
        return JWT.create()
                .withSubject(String.valueOf(userID))
                .withIssuedAt(new Date())
//                .withExpiresAt(new Date().toInstant()) TODO: setup an expiry parameter
                .sign(algorithm);

    }

    public Long retrieveUserId(String jwtString){
        var decodeJWT = JWT.decode(jwtString);
        var userId = Long.valueOf(decodeJWT.getSubject());

        return userId;

    }
}
