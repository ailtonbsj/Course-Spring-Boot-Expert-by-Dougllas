package io.github.ailtonbsj.spring_security_demo.domain.service;

import io.github.ailtonbsj.spring_security_demo.domain.entity.GroupModel;
import io.github.ailtonbsj.spring_security_demo.domain.entity.UserGroup;
import io.github.ailtonbsj.spring_security_demo.domain.entity.UserModel;
import io.github.ailtonbsj.spring_security_demo.domain.repository.GroupRepository;
import io.github.ailtonbsj.spring_security_demo.domain.repository.UserGroupRepository;
import io.github.ailtonbsj.spring_security_demo.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserModel saveUser(UserModel user, List<String> groups) {
        user.setPass(passwordEncoder.encode(user.getPass()));
        UserModel saved = repository.save(user);
        groups.stream()
                .map(groupRepository::findByName)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(group -> new UserGroup(user, group))
                .forEach(userGroupRepository::save);
        return user;
    }

    public UserModel getUserWithPermissions(String login){
        Optional<UserModel> maybe = repository.findByLogin(login);
        if(maybe.isEmpty()) return null;
        UserModel user = maybe.get();
        Optional<List<UserGroup>> maybeUG = userGroupRepository.findAllByUserModel(user);
        if(maybeUG.isEmpty()) return null;
        List<String> list = maybeUG.get().stream()
                .map(UserGroup::getGroupModel)
                .map(GroupModel::getName).distinct().toList();
        user.setPermissions(list);
        return user;
    }

    public List<UserModel> listUsers(){
        List<UserModel> users = repository.findAll();
        return users.stream().map(
                userModel -> {
                    UserModel proj = getUserWithPermissions(userModel.getLogin());
                    proj.setPass("PROTECTED");
                    return proj;
                }
        ).toList();
    }
}
