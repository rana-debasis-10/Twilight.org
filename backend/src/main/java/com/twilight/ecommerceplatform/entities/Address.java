package com.twilight.ecommerceplatform.entities;

import com.twilight.ecommerceplatform.DataToObjects.AddressDTO;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private boolean isPrimaryAddress;
    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String landMark;

    public static Address AddressDTO(AddressDTO addressDTO){
        Address address= new Address();
        address.setCity(addressDTO.getCity());
        address.setCountry(addressDTO.getCountry());
        address.setState(addressDTO.getState());
        address.setLandMark(addressDTO.getLandMark());
        address.setPostalCode(addressDTO.getPostalCode());
        address.setStreet(address.getStreet());
        address.setPrimaryAddress(false);
        return address;
    }

}
