package com.twilight.eCommercePlatform.dto.auth;

import com.twilight.eCommercePlatform.annotations.ValidEmail;
import com.twilight.eCommercePlatform.enums.UserRole;
import lombok.Data;

@Data
public class LoginRequestDTO {
    @ValidEmail
    String email;
    String password;
}
