package io.github.ailtonbsj.sboot_security.config;

import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class MasterPassAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var login = authentication.getName();
        var pass = (String) authentication.getCredentials();

        String loginMaster = "master";
        String passMaster = "@321";

        if(login.equals(loginMaster) && pass.equals(passMaster)){
            return new UsernamePasswordAuthenticationToken(
                "Master",
                null,
                List.of(new SimpleGrantedAuthority("ADMIN"))
            );
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
