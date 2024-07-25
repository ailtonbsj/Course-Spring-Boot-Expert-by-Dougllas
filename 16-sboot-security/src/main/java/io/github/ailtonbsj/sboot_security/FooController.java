package io.github.ailtonbsj.sboot_security;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class FooController {

    @GetMapping("/public")
    public ResponseEntity<String> publicRoute() {
        return ResponseEntity.ok("Public route ok");
    }

    @GetMapping("/private")
    public ResponseEntity<String> privateRoute(Authentication authentication) {
        return ResponseEntity.ok("Private route ok! " + authentication.getName());
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminRoute(Authentication authentication) {
        return ResponseEntity.ok("Admin route ok! " + authentication.getName());
    }
    
}
