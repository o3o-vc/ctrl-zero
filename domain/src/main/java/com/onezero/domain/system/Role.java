package com.onezero.domain.system;

import com.onezero.domain.Base;
import com.onezero.enums.EnableEnum;
import lombok.Data;
import org.beetl.sql.annotation.entity.Table;

@Table(name = "role")
@Data
public class Role extends Base {
    public static final Long ROLE_ID = 0L;
    private String name;
    private String code;
    private String description;
    private EnableEnum status;
}
