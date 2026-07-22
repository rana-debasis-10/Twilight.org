package com.twilight.objects;

import com.twilight.utils.annotations.MobileNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @OneToOne(cascade = CascadeType.MERGE)
    @MapsId
    @JoinColumn(name = "mob_no")
    private User user;

    @Id
    @MobileNumber
    @Column(name = "mob_no" ,length = 10)
    private String mobNo;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<CustomerLocation> addresses;


}
