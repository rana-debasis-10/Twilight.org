package com.twilight.ecommerceplatform.dto;

import com.twilight.ecommerceplatform.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String userId;
    private String name;
    private String mobNo;
    private String email;
    private String address;
    private UserRole role;
    private String password;
}
