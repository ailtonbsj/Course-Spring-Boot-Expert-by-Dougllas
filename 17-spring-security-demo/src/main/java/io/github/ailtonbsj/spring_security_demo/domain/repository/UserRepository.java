package io.github.ailtonbsj.spring_security_demo.domain.repository;

import io.github.ailtonbsj.spring_security_demo.domain.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, String> {
}
