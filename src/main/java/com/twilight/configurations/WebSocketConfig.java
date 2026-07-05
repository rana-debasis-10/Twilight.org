package com.twilight.configurations;

import com.twilight.Handlers.WebSocketHandler;
import com.twilight.interceptor.JwtHandShakeInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {
    private final WebSocketHandler handler;
    private final JwtHandShakeInterceptor jwtHandShakeInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(handler,"/ws")
                .addInterceptors(jwtHandShakeInterceptor)
                .setAllowedOrigins("*");
    }
}
