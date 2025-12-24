package com.twilight.ecommerceplatform.dto;

import lombok.Data;

import java.util.Map;

@Data
public class WebhookResponseDTO {
    private String event;
    private Map<String, Object> payload;
}
