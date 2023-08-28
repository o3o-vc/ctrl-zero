package com.onezero.service.system;

import com.mybatisflex.core.query.QueryCondition;
import com.onezero.domain.system.Menu;
import com.onezero.mapper.system.MenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.onezero.domain.system.table.MenuTableDef.MENU;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuMapper menuMapper;

    public List<Menu> all() {
        return menuMapper.selectAll();
    }

    public Boolean exist(String path, Long id) {
        QueryCondition condition = MENU.PATH.eq(path).and(MENU.ID.ne(id, !Objects.isNull(id)));
        long count = menuMapper.selectCountByCondition(condition);
        return count > 0;
    }

    public int add(Menu menu) {
        return menuMapper.insert(menu);
    }

    public int update(Menu menu) {
        if (Objects.isNull(menu.getHref())) {
            menu.setHref("");
        }
        if (Objects.isNull(menu.getSingleLayout())) {
            menu.setSingleLayout("");
        }
        return menuMapper.update(menu);
    }

    public int delete(Long id) {
        return menuMapper.deleteById(id);
    }
}
