package com.twilight.objects;

import com.twilight.utils.annotations.MobileNumber;
import com.twilight.types.OutletStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
@Builder


public class Outlet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "outlet", cascade = CascadeType.ALL)
    private List<Food> foods;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    OutletLocation location;

    @Enumerated(EnumType.STRING)
    private OutletStatus status;


    @OneToOne(cascade = CascadeType.ALL)
    Manager manager;

    @ManyToOne
    @JoinColumn(name= "mob_no")
    Merchant merchant;

    @OneToOne(cascade = CascadeType.ALL)
    OutletInvitation outletInvitation;

}
