package com.twilight.interceptor;

import com.twilight.services.JwtService;
import com.twilight.types.Role;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor

public class JwtHandShakeInterceptor implements HandshakeInterceptor {
    private final JwtService jwtService;

    @Override
    public boolean beforeHandshake(
            @NonNull ServerHttpRequest request,
            @NonNull ServerHttpResponse response,
            @NonNull WebSocketHandler wsHandler,
            @NonNull Map<String, Object> attributes
    ) {
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return false;
        }

        String token = authHeader.substring(7);

        if (!jwtService.isTokenValid(token))
            return false;

        Claims claims = jwtService.extractClaims(token);

        String mobileNo= claims.getSubject();

        Role role = claims.get("Role", Role.class);


        attributes.put("Mobile Number", mobileNo);
        attributes.put("Role",role);
        if (claims.get("Credential", Object.class)!=null) {
            attributes.put("Credential",claims.get("Credential", Object.class));
        }
        return true;
    }

    @Override
    public void afterHandshake(
            @NonNull ServerHttpRequest request,
            @NonNull ServerHttpResponse response,
            @NonNull WebSocketHandler wsHandler,
            Exception exception
    ) {

    }
}
