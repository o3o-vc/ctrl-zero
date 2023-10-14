package com.onezero.domain.system;

import com.onezero.domain.Base;
import lombok.Getter;
import lombok.Setter;
import org.beetl.sql.annotation.entity.Table;

@Table(name = "user")
@Getter
@Setter
public class User extends Base {
    private String name;
    private String username;
    private String password;
    private String status;
    private Integer age;
    private String gender;
    private String phone;
    private String email;
}
