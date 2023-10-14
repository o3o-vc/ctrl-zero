package com.onezero.mapper;

import com.onezero.domain.TestDo;
import com.onezero.domain.PageInfo;
import org.beetl.sql.core.page.PageResult;
import org.beetl.sql.mapper.BaseMapper;
import org.beetl.sql.mapper.annotation.InheritMapper;
import org.beetl.sql.mapper.annotation.SqlResource;
import org.beetl.sql.mapper.annotation.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SqlResource("test/test")
public interface TestMapper extends BaseMapper<TestDo> {

    List<TestDo> select();

    PageResult<TestDo> page(PageInfo<TestDo> pageRequest, String name);

    @Update
    @InheritMapper
    int testInsert(TestDo testDo);
}
