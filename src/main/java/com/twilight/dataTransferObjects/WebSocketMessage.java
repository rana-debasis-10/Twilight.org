package com.twilight.dataTransferObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
public class WebSocketMessage {
    String message;
    Object payload;
}
