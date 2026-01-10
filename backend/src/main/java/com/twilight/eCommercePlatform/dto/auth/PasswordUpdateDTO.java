package com.twilight.eCommercePlatform.dto.auth;

import com.twilight.eCommercePlatform.annotations.ValidEmail;
import lombok.Data;

@Data
public class PasswordUpdateDTO {
    @ValidEmail
    private String email;
    private String oldPassword;
    private String newPassword;
}
