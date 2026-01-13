package com.twilight.eCommercePlatform.entities;

import com.twilight.eCommercePlatform.annotations.ValidEmail;
import com.twilight.eCommercePlatform.dto.entity.RestaurantDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Restaurant {
    /// Unique ID of the restaurant
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /// Name of the restaurant
    private String name;

    /// Products of the restaurant
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    /// Address of the restaurant
    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    private Address address;


    @Min(value = 0)
    @Max(value = 5)
    private int rating;

    @OneToOne // Defines the 1:1 relationship
    @JoinColumn(name = "restaurantOwnerId")
    private RestaurantOwner owner;


    @ValidEmail
    @Column(nullable = false,unique = true)
    private String email;

    public Restaurant(RestaurantDTO dto){
        this.setAddress(new Address(dto.getAddressDTO()));
        this.setRating(3);
        this.setName(dto.getName());
    }
}
