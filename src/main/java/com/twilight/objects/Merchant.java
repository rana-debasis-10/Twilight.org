package com.twilight.objects;

import com.twilight.utils.annotations.MobileNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Merchants")
@NoArgsConstructor
@AllArgsConstructor
public class Merchant {
    @OneToOne
    @MapsId
    @JoinColumn(name = "mob_no")
    private User user;

    @Id
    @MobileNumber
    @Column(name = "mob_no" ,length = 10)
    private String mobNo;

    @NotNull
    String name;

    @NotBlank
    String idCard;


    @OneToOne(mappedBy = "merchant", cascade = CascadeType.ALL)
    private Restaurant restaurant;

    @OneToMany(mappedBy = "merchant",cascade = CascadeType.ALL)
    List<Outlet> outlets = new ArrayList<>();

}

