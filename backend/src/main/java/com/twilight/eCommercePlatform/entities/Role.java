package com.twilight.eCommercePlatform.entities;
import com.twilight.eCommercePlatform.enums.UserRole;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import lombok.Data;

import java.util.List;
import java.util.Set;
@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<User> users;


    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private UserRole userRole;



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_authorities",
            joinColumns = @JoinColumn(name = "roleId"),
            inverseJoinColumns = @JoinColumn(name = "authId")
    )
    private Set<Authority> authorities;

}
