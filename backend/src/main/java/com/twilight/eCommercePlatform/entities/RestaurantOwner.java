package com.twilight.eCommercePlatform.entities;

import jakarta.persistence.*;

@Entity
public class RestaurantOwner extends User{

    @OneToOne(mappedBy = "owner",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Restaurant restaurant;
}
