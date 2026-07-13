package com.twilight.objects;

import com.twilight.utils.annotations.MobileNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Manager {
   @OneToOne
   @MapsId
   @JoinColumn(name = "mob_no")
   private User user;

   @Id
   @MobileNumber
   @Column(name = "mob_no" ,length = 10)
   private String mobNo;

   @NotNull
   @OneToOne
   @JoinColumn(name ="outlet_id")
   private Outlet outlet;
}
