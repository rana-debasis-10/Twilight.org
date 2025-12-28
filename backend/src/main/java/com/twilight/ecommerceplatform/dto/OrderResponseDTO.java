package com.twilight.ecommerceplatform.dto;

import lombok.Data;

@Data
public class OrderResponseDTO {
    private String OrderId;
    private int amount;
    private String currency;
    private String key;
    private String receipt;
}
