package com.twilight.objects;

import com.twilight.types.Label;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class OrderLocation extends Location implements Serializable {
    @OneToOne(cascade = CascadeType.ALL)
    Order order;
    public OrderLocation(double latitude , double longitude, Label label) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.label = label;
    }
}
