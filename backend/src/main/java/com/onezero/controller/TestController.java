package com.onezero.controller;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.onezero.core.enhance.Params;
import com.onezero.domain.TestDo;
import com.onezero.mapper.TestMapper;
import com.onezero.domain.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestMapper testMapper;

    @GetMapping("test")
    public String test(Authentication auth) {
        TestDo testDo = new TestDo();
        testDo.setName("test");
        testDo.setMark("haha");
//        testMapper.insert(testDo);
        List<TestDo> testDos = testMapper.selectAll();
        System.out.println(testDos);
        Page<TestDo> paginate = testMapper.paginate(1, 10, new QueryWrapper());
        System.out.println(paginate);
        List<TestDo> testDos1 = testMapper.selectListByMap(Params.create().put("name", "1234").asMap());

        PageInfo<TestDo> testDoPageInfo = testMapper.page(new PageInfo<>(2, 2), "").as();
        return paginate.toString();
    }
}
