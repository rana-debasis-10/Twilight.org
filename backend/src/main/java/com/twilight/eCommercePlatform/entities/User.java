package com.twilight.eCommercePlatform.entities;

import com.twilight.eCommercePlatform.annotations.ValidMobileNumber;
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

    @Column(unique = true, nullable = false)
    private String email;

    @ValidMobileNumber
    @NotNull
    private String mobNo;

    @Column(nullable = false)
    private String password;

    private boolean accountNonExpired = true;
    private boolean accountNotLocked = true;
    private boolean credentialsNonExpired=true;
    private boolean enabled = true;



    @ManyToOne(fetch = FetchType.EAGER) // Using LAZY for better performance
    @JoinColumn(name = "roleId", nullable = false)
    private Role role; //Role of the user


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<OrderEntity> orders=new ArrayList<>();
}
