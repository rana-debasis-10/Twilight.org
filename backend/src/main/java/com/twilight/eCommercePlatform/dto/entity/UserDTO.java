package com.twilight.eCommercePlatform.dto.entity;

import com.twilight.eCommercePlatform.annotations.ValidEmail;
import com.twilight.eCommercePlatform.annotations.ValidMobileNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String name;
    @ValidMobileNumber
    private String mobNo;
    @ValidEmail
    private String email;
    private String password;
}
