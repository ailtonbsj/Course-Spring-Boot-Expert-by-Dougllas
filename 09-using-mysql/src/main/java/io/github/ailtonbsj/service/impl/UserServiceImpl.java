package io.github.ailtonbsj.service.impl;

import io.github.ailtonbsj.domain.entity.UserEntity;
import io.github.ailtonbsj.domain.repositories.UserRepository;
import io.github.ailtonbsj.exception.InvalidPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository repository;

    @Transactional
    public UserEntity save(UserEntity user){
        return repository.save(user);
    }

    public UserDetails authenticate(UserEntity user) {
        UserDetails userDetails = loadUserByUsername(user.getLogin());
        boolean matches = encoder.matches(user.getPassword(), userDetails.getPassword());
        if(matches) return userDetails;
        throw new InvalidPasswordException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        String[] roles = user.isAdmin() ?
                new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User.builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }
}
