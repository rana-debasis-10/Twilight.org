package com.twilight.objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class CustomerLocation extends Location implements Serializable {
    @OneToOne(cascade = CascadeType.ALL)
    Customer customer;
}
