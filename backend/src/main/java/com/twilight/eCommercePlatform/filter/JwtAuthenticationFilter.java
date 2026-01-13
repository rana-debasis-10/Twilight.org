package com.twilight.eCommercePlatform.filter;
import com.twilight.eCommercePlatform.enums.UserRole;
import com.twilight.eCommercePlatform.security.UserDetailsImpl;
import com.twilight.eCommercePlatform.services.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


        @Autowired
        JwtService jwtService;

        @Override
        protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {

            String authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = authHeader.substring(7);
            if (!jwtService.isTokenValid(token)) {
                filterChain.doFilter(request, response);
                return;
            }

            Claims claims = jwtService.extractClaims(token);
            @SuppressWarnings("unchecked")
            List<String> authorityStrings = (List<String>) claims.get("authorities");
            String email= claims.getSubject();

            String roleStr = claims.get("role", String.class);
            UserRole role = UserRole.valueOf(roleStr);

            Collection<GrantedAuthority> authorities = new ArrayList<>();

            authorityStrings.forEach(auth ->
                    authorities.add(new SimpleGrantedAuthority(auth))
            );
            UserDetailsImpl userDetailsImpl= new UserDetailsImpl(email, role, true, true, true, true, authorities);

            authorities.add(
                    new SimpleGrantedAuthority("ROLE_" + role.toString())
            );

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetailsImpl,
                            null,
                            authorities
                    );

            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);

            filterChain.doFilter(request, response);
        }
}
