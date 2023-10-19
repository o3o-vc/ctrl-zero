package com.onezero.service.system;


import com.onezero.base.IBase;
import com.onezero.domain.Base;
import com.onezero.domain.PageInfo;
import com.onezero.domain.system.User;
import com.onezero.mapper.system.UserMapper;
import lombok.RequiredArgsConstructor;
import org.beetl.sql.core.page.PageResult;
import org.beetl.sql.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public User get(Long id) {
        return userMapper.single(id);
    }

    public List<User> list(User user) {
        return userMapper.createLambdaQuery()
                .andEq(User::getUsername, Query.filterEmpty(user.getUsername()))
                .select();
    }

    public User getByUsername(String username) {
        /*return userMapper.createLambdaQuery()
                .andEq(User::getUsername, username)
                .unique();*/
        return userMapper.findByUsername(username);
    }

    public void add(User user) {
        userMapper.insert(user);
    }

    public int update(User user) {
        return userMapper.updateById(user);
    }

    public int delete(Long id) {
        return userMapper.deleteById(id);
    }

    public int deleteBatch(Long[] ids) {
        return userMapper.createLambdaQuery()
                .andIn("id", Arrays.stream(ids).toList())
                .delete();
    }

    public PageResult<User> page(PageInfo<User> page, User user) {
        return userMapper.createLambdaQuery()
                .andEq(User::getUsername, Query.filterEmpty(user.getUsername()))
                .page(page);
    }
}
