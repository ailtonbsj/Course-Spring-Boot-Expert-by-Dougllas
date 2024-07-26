package io.github.ailtonbsj.spring_security_demo.api.dto;

import io.github.ailtonbsj.spring_security_demo.domain.entity.UserModel;
import lombok.Data;

import java.util.List;

@Data
public class SignUpUserDTO {
    private UserModel user;
    private List<String> permissions;
}
