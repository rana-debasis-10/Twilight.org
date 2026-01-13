package com.twilight.eCommercePlatform.dto.entity;

import com.twilight.eCommercePlatform.entities.Address;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String landMark;

    public AddressDTO ( Address address ){
        if (address == null){
            throw new NullPointerException("Address can not be empty");
        }
        this.setStreet( address.getStreet() );
        this.setCity( address.getCity() );
        this.setState( address.getState() );
        this.setCountry( address.getCountry() );
        this.setPostalCode( address.getPostalCode() );
        this.setLandMark( address.getLandMark() );
    }

}
