package com.twilight.ecommerceplatform.DataToObjects;


import com.twilight.ecommerceplatform.annotations.ValidMobileNumber;
import com.twilight.ecommerceplatform.enums.Currency;
import com.twilight.ecommerceplatform.enums.PaymentMethod;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.Map;

@Data
public class OrderRequestDTO {

    @NotNull
    @Valid
    private Map<Long, @Min(1) Integer> prodIdAndQuantity;
    private  boolean useUserAddress;
    private AddressDTO address;

    private boolean useUserMobNo;
    @ValidMobileNumber
    private String mobNo;

    @NotNull
    private Currency currency;
    @NotNull
    private PaymentMethod paymentMethod;

    public boolean getUseUserMobile(){
        return useUserMobNo;
    }
    public boolean getUseUserAddress(){
        return useUserAddress;
    }

}
