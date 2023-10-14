package com.onezero.mapper.system;

import com.onezero.domain.system.Menu;
import org.beetl.sql.mapper.BaseMapper;
import org.beetl.sql.mapper.annotation.SqlResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SqlResource("system/menu")
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> listByUserId(Long userId);
}
