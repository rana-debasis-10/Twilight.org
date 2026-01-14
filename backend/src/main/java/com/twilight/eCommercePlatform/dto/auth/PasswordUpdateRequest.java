package com.twilight.eCommercePlatform.dto.auth;

import com.twilight.eCommercePlatform.annotations.ValidEmail;
import lombok.Data;

@Data
public class PasswordUpdateRequest {
    @ValidEmail
    private String email;
    private String oldPassword;
    private String newPassword;
}
