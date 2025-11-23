package com.twilight.ecommerceplatform.entities;

import com.twilight.ecommerceplatform.annotations.ValidEmail;
import com.twilight.ecommerceplatform.annotations.ValidMobileNumber;
import com.twilight.ecommerceplatform.components.Address;
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
    private Long userId;

    @NotNull(message = "PLEASE ENTER A NAME")
    private String name;

    @ValidMobileNumber(message = "PLEASE ENTER A VALID MOBILE NUMBER")
    private String mobNo;

    @Embedded
    private Address address;

    @ValidEmail
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    @NotNull(message = "ENTER PASSWORD")
    private String password;


}
