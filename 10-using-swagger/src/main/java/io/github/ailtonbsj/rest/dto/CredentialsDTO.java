package io.github.ailtonbsj.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CredentialsDTO {
    @NotNull
    private String login;
    @NotNull
    private String password;
}
