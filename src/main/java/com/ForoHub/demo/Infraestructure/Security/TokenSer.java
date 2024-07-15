package com.ForoHub.demo.Infraestructure.Security;

import com.ForoHub.demo.Domain.Users.UsersApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



@Service
public class TokenSer {
    @Value("${api.security.secret}")
    private String apiSecret;
    public String generateToken(UsersApi user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("forohub")
                    .withSubject(user.getLogin())
                    .withClaim("id", user.getId())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Invalid Signing configuration / Couldn't convert Claims.");
        }
    }

    public String getSubject(String token) {
        if (token == null){
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    .withIssuer("forohub")
                    .build()
                    .verify(token);
            verifier.getSubject();

        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }
        if (verifier == null || verifier.getSubject() == null){
            throw new RuntimeException("Token invalid");
        }
        return verifier.getSubject();

    }
}
