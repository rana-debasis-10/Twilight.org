package com.twilight.eCommercePlatform.entities;

import com.twilight.eCommercePlatform.annotations.ValidEmail;
import com.twilight.eCommercePlatform.annotations.ValidMobileNumber;
import com.twilight.eCommercePlatform.enums.UserRole;
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
    private Long Id;
    @NotNull(message = "Enter a name")
    private String name;

    @ValidEmail
    @Column(unique = true, nullable = false)
    private String email;

    @ValidMobileNumber
    @NotNull
    private String mobNo;

    @Column(nullable = false)
    private String password;

    @Enumerated (EnumType.STRING)
    private UserRole role;

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired=true;
    private boolean enabled = true;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<OrderEntity> orders=new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Driver driver;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private RestaurantOwner restaurantOwner;

}
