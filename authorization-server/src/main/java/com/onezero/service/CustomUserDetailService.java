package com.onezero.service;

import com.mybatisflex.core.query.QueryChain;
import com.onezero.domain.User;
import com.onezero.domain.table.UserTableDef;
import com.onezero.model.SecurityUser;
import com.onezero.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new QueryChain<>(userRepository)
                .where(UserTableDef.USER.USERNAME.eq(username))
                .one();
        return new SecurityUser(user);
    }
}
