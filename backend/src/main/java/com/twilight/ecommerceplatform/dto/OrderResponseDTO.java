package com.twilight.ecommerceplatform.DataToObjects;

import lombok.Data;

@Data
public class OrderResponseDTO {
    private String OrderId;
    private int amount;
    private String currency;
    private String key;
    private String receipt;
}
