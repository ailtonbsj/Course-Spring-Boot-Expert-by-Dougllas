package io.github.ailtonbsj.spring_security_demo.domain.service;

import io.github.ailtonbsj.spring_security_demo.domain.entity.UserGroup;
import io.github.ailtonbsj.spring_security_demo.domain.entity.UserModel;
import io.github.ailtonbsj.spring_security_demo.domain.repository.GroupRepository;
import io.github.ailtonbsj.spring_security_demo.domain.repository.UserGroupRepository;
import io.github.ailtonbsj.spring_security_demo.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final GroupRepository groupRepository;
    private final UserGroupRepository userGroupRepository;

    @Transactional
    public UserModel saveUser(UserModel user, List<String> groups) {
        UserModel saved = repository.save(user);
        groups.stream()
                .map(groupRepository::findByName)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(group -> new UserGroup(user, group))
                .forEach(userGroupRepository::save);
        return user;
    }
}
