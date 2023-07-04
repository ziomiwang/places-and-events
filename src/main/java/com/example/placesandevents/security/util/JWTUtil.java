package com.example.placesandevents.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;


@Component
public class JWTUtil {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.issuer}")
    private String issuer;


    public String getSubject(final String token) {
        return JWT.decode(token).getSubject();
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        final Algorithm algorithm = Algorithm.HMAC512(secretKey);
        JWTCreator.Builder builder = JWT.create()
                .withExpiresAt(Instant.now().plus(expiration, ChronoUnit.MINUTES))
                .withIssuer(issuer)
                .withSubject(userDetails.getUsername());

        for (Map.Entry<String, Object> entry : extraClaims.entrySet()) {
            builder = builder.withClaim(entry.getKey(), String.valueOf(entry.getValue()));
        }

        return builder.sign(algorithm);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final Algorithm algorithm = Algorithm.HMAC512(secretKey);
        final DecodedJWT decodedJWT = JWT.require(algorithm)
                .build()
                .verify(token);

        final Date expiresAt = decodedJWT.getExpiresAt();
        final String subject = decodedJWT.getSubject();

        final Predicate<String> subjectPredicate = subjectValue -> subjectValue.equals(userDetails.getUsername());
        final Predicate<Date> expirationPredicate = expirationAt -> !expirationAt.before(new Date());

        return subjectPredicate.test(subject) && expirationPredicate.test(expiresAt);
    }
}
