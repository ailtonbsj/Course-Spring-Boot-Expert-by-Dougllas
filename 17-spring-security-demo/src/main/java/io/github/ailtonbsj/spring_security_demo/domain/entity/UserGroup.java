package io.github.ailtonbsj.spring_security_demo.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_model_id")
    private UserModel userModel;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupModel groupModel;

    public UserGroup(UserModel userModel, GroupModel groupModel) {
        this.userModel = userModel;
        this.groupModel = groupModel;
    }
}
