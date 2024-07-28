package io.github.ailtonbsj.spring_security_demo.domain.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

public class CustomAuthentication implements Authentication {
    private final UserMultipass userMultipass;

    public CustomAuthentication(UserMultipass userMultipass) {
        if(userMultipass == null) throw new ExceptionInInitializerError("Need a UserMultipass!");
        this.userMultipass = userMultipass;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userMultipass.getPermissions().stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.userMultipass;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalArgumentException("Already logged!");
    }

    @Override
    public String getName() {
        return this.userMultipass.getName();
    }
}
