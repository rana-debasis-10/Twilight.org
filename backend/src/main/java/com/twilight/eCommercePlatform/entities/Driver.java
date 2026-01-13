package com.twilight.eCommercePlatform.entities;

import jakarta.persistence.*;

@Entity
public class Driver {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
