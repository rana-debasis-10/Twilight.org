package com.twilight.serviceImpls;

import com.twilight.services.JwtService;
import com.twilight.utils.Constants;
import com.twilight.types.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.constraints.NotBlank;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Set;

@Service

public class JwtServiceImpl implements JwtService {
    @Value("${jwt.secret.key}")
    private String secretKey ;

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    @Override
    public String generateAccessToken(String mobNo, Set<Role> role) {
        return Jwts.builder()
                .subject(mobNo)
                .claim(Constants.ROLE,role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date((System.currentTimeMillis() + 3600000)))
                .signWith(getKey())
                .compact();
    }

    @Override
    public String generateRefreshToken(String mobNo) {
        return Jwts.builder()
                .subject(mobNo)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date((long)(System.currentTimeMillis() + 2.628e+9)))
                .signWith(getKey())
                .compact();
    }

    @Override
    public boolean isValid(@NonNull String token){
            try {
                extractClaims(token);
                return true;
            } catch (Exception e) {
                return false;
            }
    }
    @Override
    public Claims extractClaims(@NotBlank String token){
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
