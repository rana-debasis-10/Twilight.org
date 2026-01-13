package com.twilight.eCommercePlatform.dto.entity;

import com.twilight.eCommercePlatform.entities.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {
    private String name;
    private AddressDTO addressDTO;
    public RestaurantDTO (Restaurant restaurant){
        this.setAddressDTO(new AddressDTO(restaurant.getAddress()));
        this.setName(restaurant.getName());
    }
}
