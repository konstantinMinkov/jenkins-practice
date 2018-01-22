package com.zakedy.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.Data;
import lombok.experimental.Accessors;

import static java.util.Collections.singletonList;

@Data
@Accessors(chain = true)
@Entity
public class User implements UserDetails {

    @Id
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return singletonList(authority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
