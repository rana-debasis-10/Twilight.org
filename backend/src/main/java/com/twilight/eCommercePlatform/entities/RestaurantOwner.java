package com.twilight.eCommercePlatform.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "restaurant_owner")
public class RestaurantOwner {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(unique = true,nullable = false)
    private String email;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;



    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Restaurant restaurant;
}

