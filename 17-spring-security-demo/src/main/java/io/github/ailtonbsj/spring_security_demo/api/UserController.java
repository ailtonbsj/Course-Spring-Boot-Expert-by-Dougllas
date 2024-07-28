package io.github.ailtonbsj.spring_security_demo.api;

import io.github.ailtonbsj.spring_security_demo.api.dto.SignUpUserDTO;
import io.github.ailtonbsj.spring_security_demo.domain.entity.UserModel;
import io.github.ailtonbsj.spring_security_demo.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserModel> save(@RequestBody SignUpUserDTO body) {
        UserModel user = userService.saveUser(body.getUser(), body.getPermissions());
        return ResponseEntity.ok(user);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserModel>> list() {
        return ResponseEntity.ok(userService.listUsers());
    }
}
