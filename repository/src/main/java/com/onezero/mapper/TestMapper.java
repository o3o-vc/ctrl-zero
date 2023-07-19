package com.onezero.mapper;

import com.mybatisflex.core.BaseMapper;
import com.onezero.domain.TestDo;
import com.onezero.domain.PageInfo;
import com.onezero.model.RPage;
import org.springframework.stereotype.Repository;

@Repository
public interface TestMapper extends BaseMapper<TestDo> {
    RPage<TestDo> page(PageInfo<TestDo> pageInfo, String arg);
}
