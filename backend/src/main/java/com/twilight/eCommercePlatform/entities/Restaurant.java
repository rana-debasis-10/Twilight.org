package com.twilight.eCommercePlatform.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table
@Data
public class Restaurant {
    /// Unique ID of the restaurant
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restId;

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
    @JoinColumn(name = "restaurantOwnerUser_userId")
    private RestaurantOwner owner;
}
