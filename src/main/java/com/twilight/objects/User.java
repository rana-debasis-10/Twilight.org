package com.twilight.objects;

import com.twilight.utils.annotations.MobileNumber;
import com.twilight.types.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    @Column(name = "mob_no")
    @MobileNumber
    String mobNo;

    @Enumerated(EnumType.STRING)
    Role role;

    String credential;

    boolean blocked;

    @OneToOne(mappedBy = "user")
    Customer customer;

    @OneToOne(mappedBy = "user")
    Manager manager;

    @OneToOne(mappedBy = "user")
    Merchant merchant;

    @OneToOne(mappedBy = "user")
    Driver driver;

}
