package com.twilight.ecommerceplatform.entities;

import com.twilight.ecommerceplatform.annotations.ValidEmail;
import com.twilight.ecommerceplatform.annotations.ValidMobileNumber;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")   // FIXED
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; // Primary Key and User ID

    @NotNull(message = "PLEASE ENTER A NAME")
    private String name; // Name of User

    @ValidMobileNumber(message = "PLEASE ENTER A VALID MOBILE NUMBER")
    private String mobNo; // Mobile Number of the User


    @ValidEmail
    private String email; // Email of User

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    @Nullable
    private Address address; // Primary Address of the User


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<OrderEntity> orders=new ArrayList<>();
}
