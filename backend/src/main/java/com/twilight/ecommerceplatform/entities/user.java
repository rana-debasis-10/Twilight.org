package com.twilight.ecommerceplatform.entities;
import com.twilight.ecommerceplatform.annotations.ValidEmail;
import com.twilight.ecommerceplatform.annotations.ValidMobileNumber;
import com.twilight.ecommerceplatform.components.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Entity
@Component
@Data
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String user_id;
    @NotNull(message = "PLEASE ENTER A NAME")
    private String name;
    @ValidMobileNumber(message = "PLEASE ENTER A VALID MOBILE NUMBER")
    private String mobNo;
    private Address address;
    @ValidEmail
    private String email;
    @OneToMany(mappedBy = "user",cascade= CascadeType.ALL)
    private ArrayList<order> orders;
}
