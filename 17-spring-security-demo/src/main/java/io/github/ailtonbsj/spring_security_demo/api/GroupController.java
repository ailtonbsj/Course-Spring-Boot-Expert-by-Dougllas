package io.github.ailtonbsj.spring_security_demo.api;

import io.github.ailtonbsj.spring_security_demo.domain.entity.GroupModel;
import io.github.ailtonbsj.spring_security_demo.domain.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupRepository repository;

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GroupModel> save(@RequestBody GroupModel group) {
        repository.save(group);
        return ResponseEntity.ok(group);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<GroupModel>> save() {
        return ResponseEntity.ok(repository.findAll());
    }

}
