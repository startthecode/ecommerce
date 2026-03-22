package org.authetication.ecommerce.services.imp;

import java.util.List;
import java.util.Collection;

import org.authetication.ecommerce.entity.user.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipleImp implements UserDetails {

    private final UserEntity user;

    public UserPrincipleImp(UserEntity user) {
        this.user = user;
    }

    /** Expose the wrapped entity so controllers can access User fields */
    public UserEntity getUser() {
        return user;
    }

    // ── UserDetails contract ─────────────────────────────────────────────────

    @Override
    public String getUsername() {
        return user.getEmail();          // we use email as the login identifier
    }

    @Override
    public String getPassword() {
        return user.getPassword();       // BCrypt hash
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getRole()));
    }

    @Override public boolean isEnabled()               { return true; }
    @Override public boolean isAccountNonExpired()     { return true; }
    @Override public boolean isAccountNonLocked()      { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
}

