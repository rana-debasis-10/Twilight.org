package com.twilight.eCommercePlatform.entities;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String landMark;
    @OneToOne(mappedBy = "address",fetch = FetchType.LAZY)
    private OrderEntity order;
}
