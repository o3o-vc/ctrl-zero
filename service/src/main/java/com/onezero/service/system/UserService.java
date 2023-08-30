package com.onezero.service.system;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.QueryWrapper;
import com.onezero.domain.system.User;
import com.onezero.mapper.system.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static com.onezero.domain.system.table.UserTableDef.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public User get(Long id) {
        return userMapper.selectOneById(id);
    }

    public List<User> list(User user) {
        QueryCondition condition = USER.USERNAME.eq(user.getUsername(), false);
        return userMapper.selectListByCondition(condition);
    }

    public User getByUsername(String username) {
        return userMapper.selectOneByCondition(
                USER.USERNAME.eq(username)
        );
    }

    public int add(User user) {
        int inserted = userMapper.insert(user);
        System.out.println(user);
        return inserted;
    }

    public int update(User user) {
        return userMapper.update(user);
    }

    public int delete(Long id) {
        return userMapper.deleteById(id);
    }

    public int deleteBatch(Long[] ids) {
        return userMapper.deleteBatchByIds(Arrays.stream(ids).toList());
    }

    public Page<User> page(Page<User> page, User user) {
        QueryWrapper queryWrapper = QueryWrapper.create(new User());
        return userMapper.paginate(page, queryWrapper);
    }
}
