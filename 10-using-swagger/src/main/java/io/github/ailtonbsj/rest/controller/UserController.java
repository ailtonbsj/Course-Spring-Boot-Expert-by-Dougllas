package io.github.ailtonbsj.rest.controller;

import io.github.ailtonbsj.domain.entity.UserEntity;
import io.github.ailtonbsj.exception.InvalidPasswordException;
import io.github.ailtonbsj.rest.dto.CredentialsDTO;
import io.github.ailtonbsj.rest.dto.TokenDTO;
import io.github.ailtonbsj.security.JWTService;
import io.github.ailtonbsj.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@Api("Creation and Authentication of users")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity save(@RequestBody @Valid UserEntity user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.save(user);
    }

    @PostMapping("/auth")
    @ApiOperation("Generate a JWT Token with user credentials")
    public TokenDTO authenticate(@RequestBody CredentialsDTO credentials) {
        try {
            UserEntity user = UserEntity.builder()
                    .login(credentials.getLogin())
                    .password(credentials.getPassword())
                    .build();
            UserDetails authenticated = userService.authenticate(user);
            String token = jwtService.generateToken(user);
            return new TokenDTO(user.getLogin(), token);
        } catch (UsernameNotFoundException | InvalidPasswordException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
