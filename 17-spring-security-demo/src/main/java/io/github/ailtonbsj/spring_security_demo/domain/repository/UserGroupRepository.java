package io.github.ailtonbsj.spring_security_demo.domain.repository;

import io.github.ailtonbsj.spring_security_demo.domain.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRepository extends JpaRepository<UserGroup, String> {
}
