package com.onezero.domain.system;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

@Table("role_menu_mapping")
@Data(staticConstructor = "create")
@Accessors(chain = true)
public class RoleMenuMapping {
    @Id(keyType = KeyType.None)
    private Long roleId;
    @Id(keyType = KeyType.None)
    private Long menuId;
    private Boolean isMark;
}
