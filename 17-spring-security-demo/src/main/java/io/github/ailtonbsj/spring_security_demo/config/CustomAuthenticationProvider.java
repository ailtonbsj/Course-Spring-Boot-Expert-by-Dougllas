package io.github.ailtonbsj.spring_security_demo.config;

import io.github.ailtonbsj.spring_security_demo.domain.entity.UserModel;
import io.github.ailtonbsj.spring_security_demo.domain.security.CustomAuthentication;
import io.github.ailtonbsj.spring_security_demo.domain.security.UserMultipass;
import io.github.ailtonbsj.spring_security_demo.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String pass = (String) authentication.getCredentials();
        UserModel user = userService.getUserWithPermissions(login);
        if(user != null) {
            boolean test = passwordEncoder.matches(pass, user.getPass());
            if(passwordEncoder.matches(pass, user.getPass())){
                UserMultipass multipass = new UserMultipass(
                        user.getId(), user.getName(), user.getName(),
                        user.getPermissions()
                );
                return new CustomAuthentication(multipass);
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
