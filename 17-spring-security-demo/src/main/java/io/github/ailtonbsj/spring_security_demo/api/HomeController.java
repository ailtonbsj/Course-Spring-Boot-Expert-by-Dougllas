package io.github.ailtonbsj.spring_security_demo.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HomeController {
    @GetMapping("")
    public String home(Authentication authentication){
        return "Private Home! Mr(a). " + authentication.getName();
    }

    @GetMapping("/public")
    public String publicRoute() {
        return "Public!";
    }

    @GetMapping("/private")
    @PreAuthorize("hasRole('USER')")
    public String privateRoute() {
        return "Private for role USER!";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminRoute() {
        return "Private for role ADMIN!";
    }
}
