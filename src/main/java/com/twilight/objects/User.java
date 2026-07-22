package com.twilight.objects;

import com.twilight.utils.annotations.MobileNumber;
import com.twilight.types.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@Builder
public class User {
    @Id
    @Column(name = "mob_no")
    @MobileNumber
    String mobNo;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "role")
    private Set<Role> roles = new HashSet<>();

    boolean blocked = false;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    Customer customer;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    Manager manager;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    Merchant merchant;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    Driver driver;

}
