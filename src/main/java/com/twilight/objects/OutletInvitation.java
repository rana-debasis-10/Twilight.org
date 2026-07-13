package com.twilight.objects;

import com.twilight.utils.annotations.MobileNumber;
import com.twilight.types.InvitationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OutletInvitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "mob_no")
    Merchant merchant;

    @MobileNumber
    private String inviterMobileNo;

    @NotNull
    @JoinColumn(name ="outlet_id")
    Outlet outlet;

    @Enumerated(EnumType.STRING)
    private InvitationStatus status;

}
