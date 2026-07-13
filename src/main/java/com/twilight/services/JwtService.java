package com.twilight.services;

import com.twilight.utils.annotations.MobileNumber;
import com.twilight.types.Role;
import io.jsonwebtoken.Claims;
import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.NonNull;

public interface JwtService {

    String generateToken(@MobileNumber @NotNull String mobNo, @NonNull Role role) ;

    String generateToken(@MobileNumber @NotNull String mobNo , @NonNull Role role , Object credential);

    String generateToken(@MobileNumber @NotNull String mobNo , @NonNull Role role , Object credential, Long lifespan);

    String generateToken(String mobNo, @NonNull Role role, Long lifespan);

    boolean isTokenValid(@NonNull @NotNull String token) ;

    Claims extractClaims(@NonNull @NotNull String token) ;


}
