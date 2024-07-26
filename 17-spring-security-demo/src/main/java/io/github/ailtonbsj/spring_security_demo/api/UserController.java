package io.github.ailtonbsj.spring_security_demo.api;

import io.github.ailtonbsj.spring_security_demo.api.dto.SignUpUserDTO;
import io.github.ailtonbsj.spring_security_demo.domain.entity.GroupModel;
import io.github.ailtonbsj.spring_security_demo.domain.entity.UserModel;
import io.github.ailtonbsj.spring_security_demo.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
