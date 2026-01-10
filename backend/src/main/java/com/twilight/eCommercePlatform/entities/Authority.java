package com.twilight.eCommercePlatform.entities;

import com.twilight.eCommercePlatform.enums.AuthWork;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import java.util.Set;

@Entity
@Data
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private AuthWork authWork;

    @ManyToMany(mappedBy = "authorities")
    private Set<Role> roles;

    @Override
    public String getAuthority() {
        return this.authWork.toString();
    }
}
