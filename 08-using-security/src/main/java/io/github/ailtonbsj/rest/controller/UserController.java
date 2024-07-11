package io.github.ailtonbsj.rest.controller;

import io.github.ailtonbsj.domain.entity.UserEntity;
import io.github.ailtonbsj.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity save(@RequestBody @Valid UserEntity user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.save(user);
    }
}
