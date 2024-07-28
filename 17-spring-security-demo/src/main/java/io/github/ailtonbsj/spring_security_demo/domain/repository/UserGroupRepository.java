package io.github.ailtonbsj.spring_security_demo.domain.repository;

import io.github.ailtonbsj.spring_security_demo.domain.entity.GroupModel;
import io.github.ailtonbsj.spring_security_demo.domain.entity.UserGroup;
import io.github.ailtonbsj.spring_security_demo.domain.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserGroupRepository extends JpaRepository<UserGroup, String> {
    Optional<List<UserGroup>> findAllByUserModel(UserModel user);

    @Query("""
        select distinct g.name from UserGroup ug
        join ug.groupModel g
        join ug.userModel u
        where u = ?1
    """)
    Optional<List<String>> findPermissionsByUsers(UserModel user);
}
