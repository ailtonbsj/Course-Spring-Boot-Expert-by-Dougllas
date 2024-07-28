package io.github.ailtonbsj.spring_security_demo.domain.security;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UserMultipass {
    private String id;
    private String name;
    private String login;
    private List<String> permissions;

    public List<String> getPermissions() {
        return permissions == null ?
                new ArrayList<>() : permissions;
    }
}
