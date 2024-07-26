package io.github.ailtonbsj.spring_security_demo.domain.repository;

import io.github.ailtonbsj.spring_security_demo.domain.entity.GroupModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<GroupModel, String> {
    Optional<GroupModel> findByName(String name);
}
