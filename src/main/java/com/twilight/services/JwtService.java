package com.twilight.services;

import com.twilight.utils.annotations.MobileNumber;
import com.twilight.types.Role;
import io.jsonwebtoken.Claims;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public interface JwtService {
    String generateAccessToken(@MobileNumber String mobNo, Set<Role> role);
    String generateRefreshToken(@MobileNumber String mobNo);
    boolean isValid(String token) ;
    Claims extractClaims(@NotBlank String token) ;
}
