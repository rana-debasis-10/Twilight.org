package com.twilight.eCommercePlatform.dto.order;


import com.twilight.eCommercePlatform.annotations.ValidMobileNumber;
import com.twilight.eCommercePlatform.dto.entity.AddressDTO;
import com.twilight.eCommercePlatform.enums.Currency;
import com.twilight.eCommercePlatform.enums.PaymentMethod;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.Map;

/// For creating order
@Data
public class OrderRequestDTO {

    @NotNull
    @Valid
    private Map<Long, @Min(1) Integer> prodIdAndQuantity;
    private AddressDTO address;

    public boolean defaultMobNo;

    @ValidMobileNumber
    private String mobNo;

    @NotNull
    private Currency currency;

    @NotNull
    private PaymentMethod paymentMethod;

}
