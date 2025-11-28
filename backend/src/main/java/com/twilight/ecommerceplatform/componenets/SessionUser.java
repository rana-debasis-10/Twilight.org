package com.twilight.ecommerceplatform.componenets;

import lombok.Data;

@Data
public class SessionUser {
    private long userId;
    private String mobNo;
    private long primaryAddressId;
}
