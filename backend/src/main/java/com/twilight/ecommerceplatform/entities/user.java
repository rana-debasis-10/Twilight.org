package com.twilight.ecommerceplatform.entities;
import com.twilight.ecommerceplatform.annotations.ValidEmail;
import com.twilight.ecommerceplatform.annotations.ValidMobileNumber;
import com.twilight.ecommerceplatform.components.Address;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Data
@Document(collection="users")
public class user {
    @NotNull(message = "PLEASE ENTER A NAME")
    private String name;
    @Id
    @ValidMobileNumber(message = "PLEASE ENTER A VALID MOBILE NUMBER")
    private String mobNo;
    @Nullable
    private Address address;
    @Nullable
    @ValidEmail
    private String email;
    @DBRef
    private ArrayList<order> orders;


}
