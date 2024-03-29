package dev.clima.securityjwt.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt_secret}")
    private String secret;

    @Value("${token_lifetime}")
    private Integer lifetime;

    private final static String SUBJECT = "User Details";

    private final static String COMPANY_NAME = "CLIMA COMPANY";

    public String generateToken(String email) throws IllegalArgumentException, JWTCreationException {
        return JWT.create()
                .withSubject(SUBJECT)
                .withClaim("email", email)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withIssuer(COMPANY_NAME)
                .withExpiresAt(new Date(System.currentTimeMillis() + lifetime * 60 * 1000))
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveSubject(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject(SUBJECT)
                .withIssuer(COMPANY_NAME)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("email").asString();
    }

}
