package com.onezero.mapper.system;

import com.onezero.domain.system.User;
import org.beetl.sql.mapper.BaseMapper;
import org.beetl.sql.mapper.annotation.SpringData;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
    @SpringData
    User findByUsername(String username);
}
