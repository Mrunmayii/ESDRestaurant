package org.mrunmayi.restaurant.helpers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;


@Component
@RequiredArgsConstructor
public class JWTAuthHelper {

    private final JWTHelper jwtHelper;

    public String checkJWT(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer")) {
            String token = header.substring(7);
            System.out.println("token is: "+token);
            try {
                if (jwtHelper.validateToken(token)) {
                    return jwtHelper.extractUsername(token);
                }
            } catch (ExpiredJwtException e) {
                System.out.println("Token has expired: " + e.getMessage());
            } catch (SignatureException e) {
                System.out.println("Invalid token signature: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Token validation error: " + e.getMessage());
            }
        }
        return null; //null if token is invalid or not present
    }
}
