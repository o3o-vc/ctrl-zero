package com.onezero.domain.system;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.beetl.sql.annotation.entity.AssignID;
import org.beetl.sql.annotation.entity.Table;

@Table(name = "role_user_mapping")
@Data()
public class RoleUserMapping {

    @AssignID
    private Long roleId;
    @AssignID
    private Long userId;
}
