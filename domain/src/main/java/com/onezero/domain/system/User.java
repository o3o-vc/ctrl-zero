package com.onezero.domain.system;

import com.mybatisflex.annotation.Table;
import com.onezero.domain.Base;
import lombok.Getter;
import lombok.Setter;

@Table("user")
@Getter
@Setter
public class User extends Base {
    private String username;
    private String password;
    private String status;
    private Integer age;
    private String gender;
    private String phone;
    private String email;
}
