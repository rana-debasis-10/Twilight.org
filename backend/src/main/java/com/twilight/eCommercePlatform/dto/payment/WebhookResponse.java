package com.twilight.eCommercePlatform.dto.payment;

import lombok.Data;

@Data
public class WebhookResponse {
    String payload;
    String signature;

}
