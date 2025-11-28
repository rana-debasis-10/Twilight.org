package com.twilight.ecommerceplatform.DataToObjects;

import lombok.Data;

@Data
public class AddressDTO {
    private long addressId;
    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String landMark;

}
