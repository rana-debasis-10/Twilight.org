package com.twilight.eCommercePlatform.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private long addressId;
    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String landMark;

}
