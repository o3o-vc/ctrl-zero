package com.onezero.domain;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

@Table("user")
@Data
public class User {

    @Id(keyType = KeyType.Auto)
    private Long id;
    private String username;
    private String password;
    private String authority;

}
