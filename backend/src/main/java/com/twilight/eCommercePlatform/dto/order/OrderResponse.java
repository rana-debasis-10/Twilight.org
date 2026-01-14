package com.twilight.eCommercePlatform.dto.order;

import lombok.Data;

/// After order is created this is for response to the user
@Data
public class OrderResponse {
    private String OrderId;
    private int amount;
    private String currency;
    private String key;
    private String receipt;
}
