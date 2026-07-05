package com.twilight.objects;

import com.twilight.annotations.MobileNumber;
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

    @MobileNumber
    private String inviteeMobileNo;

    @MobileNumber
    private String inviterMobileNo;

    @NotNull
    @Column(unique = true)
    private Integer outletId;

    @Enumerated(EnumType.STRING)
    private InvitationStatus status;

}
