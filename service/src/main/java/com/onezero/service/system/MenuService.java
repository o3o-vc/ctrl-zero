package com.onezero.service.system;

import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.QueryWrapper;
import com.onezero.domain.system.Menu;
import com.onezero.domain.system.Role;
import com.onezero.domain.system.RoleMenuMapping;
import com.onezero.domain.system.table.RoleMenuMappingTableDef;
import com.onezero.domain.system.table.RoleTableDef;
import com.onezero.domain.system.table.RoleUserMappingTableDef;
import com.onezero.enums.EnableEnum;
import com.onezero.mapper.system.MenuMapper;
import com.onezero.mapper.system.RoleMenuMappingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.onezero.domain.system.table.MenuTableDef.MENU;
import static com.onezero.domain.system.table.RoleMenuMappingTableDef.ROLE_MENU_MAPPING;
import static com.onezero.domain.system.table.RoleTableDef.ROLE;
import static com.onezero.domain.system.table.RoleUserMappingTableDef.ROLE_USER_MAPPING;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuMapper menuMapper;
    private final RoleMenuMappingMapper roleMenuMappingMapper;

    public List<Menu> listByUser(Long userId) {
        return QueryChain.of(menuMapper)
                .select(MENU.ALL_COLUMNS)
                .from(MENU.as("m"), ROLE_MENU_MAPPING.as("rm"), ROLE.as("r"), ROLE_USER_MAPPING.as("ru"))
                .where(MENU.ID.eq(ROLE_MENU_MAPPING.MENU_ID))
                .and(ROLE_MENU_MAPPING.ROLE_ID.eq(ROLE_USER_MAPPING.ROLE_ID))
                .and(ROLE_USER_MAPPING.USER_ID.eq(userId))
                .and(ROLE.ID.eq(ROLE_MENU_MAPPING.ROLE_ID))
                .and(ROLE.STATUS.eq(EnableEnum.ENABLE))
                .groupBy(MENU.ID)
                .list();
    }

    public List<Menu> all() {
        return menuMapper.selectAll();
    }
    public Menu get(Long id) {
        return menuMapper.selectOneById(id);
    }

    public Boolean exist(String path, Long id) {
        QueryCondition condition = MENU.PATH.eq(path).and(MENU.ID.ne(id, Objects::nonNull));
        long count = menuMapper.selectCountByCondition(condition);
        return count > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public int add(Menu menu) {
        int count = menuMapper.insert(menu);
        if (!menu.getRequiresAuth()) {
            roleMenuMappingMapper.insert(RoleMenuMapping.create().setMenuId(menu.getId()).setRoleId(Role.ROLE_ID));

        }
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    public int update(Menu menu) {
        if (Objects.isNull(menu.getHref())) {
            menu.setHref("");
        }
        if (Objects.isNull(menu.getSingleLayout())) {
            menu.setSingleLayout("");
        }
        return menuMapper.update(menu);
    }

    @Transactional(rollbackFor = Exception.class)
    public int delete(Long id) {
        return menuMapper.deleteById(id);
    }
}
