package com.twilight.ecommerceplatform.components;

import lombok.Data;

@Data
public class Address {
    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private Double latitude;   // optional
    private Double longitude;
}
