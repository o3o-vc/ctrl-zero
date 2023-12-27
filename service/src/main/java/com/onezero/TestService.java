package com.onezero;

import com.onezero.domain.system.User;
import com.onezero.mapper.TestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestMapper testMapper;

    public void test() {
        User user = new User();
    }

}
