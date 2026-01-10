package com.twilight.eCommercePlatform.dto.payment;

import lombok.Data;

@Data
public class WebhookResponseDTO {
    String payload;
    String signature;

}
