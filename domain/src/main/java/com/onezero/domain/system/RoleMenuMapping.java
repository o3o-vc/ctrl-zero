package com.onezero.domain.system;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.beetl.sql.annotation.entity.AssignID;
import org.beetl.sql.annotation.entity.Table;

@Table(name = "role_menu_mapping")
@Data
public class RoleMenuMapping {

    @AssignID
    private Long roleId;
    @AssignID
    private Long menuId;
    private Boolean isMark;
}
