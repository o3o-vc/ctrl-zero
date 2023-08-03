package com.onezero.repository;

import com.mybatisflex.core.BaseMapper;
import com.onezero.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseMapper<User> {
}
