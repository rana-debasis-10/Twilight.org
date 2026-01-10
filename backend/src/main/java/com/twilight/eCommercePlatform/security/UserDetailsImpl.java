package com.twilight.eCommercePlatform.security;

import com.twilight.eCommercePlatform.entities.Role;
import com.twilight.eCommercePlatform.entities.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

@Data
public class UserDetailsImpl implements UserDetails {
    private String email;
    private String password;
    private Role role;
    private String mobNo;
    private boolean accountNonExpired=true;
    private boolean accountNotLocked=true;
    private boolean credentialsNonExpired=true;
    private boolean enabled=true;
    private Collection< ? extends GrantedAuthority> authorities;

    public UserDetailsImpl(User user){
        this.email=user.getEmail();
        this.password = user.getPassword();
        this. authorities = user.getRole().getAuthorities();
        this.accountNonExpired= user.isAccountNonExpired();
        this.accountNotLocked= this.isAccountNonLocked();
        this.credentialsNonExpired= user.isCredentialsNonExpired();
        this.enabled= user.isEnabled();
        this.role=user.getRole();
    }
    public UserDetailsImpl(String email){
        this.email=email;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNotLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
