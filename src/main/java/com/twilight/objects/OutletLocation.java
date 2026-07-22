package com.twilight.objects;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;


@Entity
@Getter
@Setter
public class OutletLocation extends Location implements Serializable {
    @OneToOne(mappedBy = "location")
    private Outlet outlet;
}
