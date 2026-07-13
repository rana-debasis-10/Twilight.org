package com.twilight.objects;

import com.twilight.utils.annotations.MobileNumber;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    @OneToOne
    @MapsId
    @JoinColumn(name = "mob_no")
    private User user;

    @Id
    @MobileNumber
    @Column(name = "mob_no" , length = 10)
    private String mobNo;

    private String name;
    private String drivingLicense;
    private String pan;
    private String aadhaar;
    private String bankAccount;
    private String ifsc;

}
