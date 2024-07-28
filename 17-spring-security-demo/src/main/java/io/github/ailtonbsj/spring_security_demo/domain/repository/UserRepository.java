package io.github.ailtonbsj.spring_security_demo.domain.repository;

import io.github.ailtonbsj.spring_security_demo.domain.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, String> {
    Optional<UserModel> findByLogin(String login);
}
