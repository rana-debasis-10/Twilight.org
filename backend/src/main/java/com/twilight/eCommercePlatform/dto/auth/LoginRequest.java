package com.twilight.eCommercePlatform.dto.auth;

import com.twilight.eCommercePlatform.annotations.ValidEmail;
import lombok.Data;

@Data
public class LoginRequest {
    @ValidEmail
    String email;
    String password;
}
