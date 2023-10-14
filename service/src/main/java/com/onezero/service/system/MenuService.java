package com.onezero.service.system;


import com.onezero.domain.system.Menu;
import com.onezero.domain.system.Role;
import com.onezero.domain.system.RoleMenuMapping;
import com.onezero.enums.EnableEnum;
import com.onezero.mapper.system.MenuMapper;
import com.onezero.mapper.system.RoleMenuMappingMapper;
import lombok.RequiredArgsConstructor;
import org.beetl.sql.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuMapper menuMapper;
    private final RoleMenuMappingMapper roleMenuMappingMapper;

    public List<Menu> listByUser(Long userId) {
        /*return QueryChain.of(menuMapper)
                .select(MENU.ALL_COLUMNS)
                .from(MENU.as("m"), ROLE_MENU_MAPPING.as("rm"), ROLE.as("r"), ROLE_USER_MAPPING.as("ru"))
                .where(MENU.ID.eq(ROLE_MENU_MAPPING.MENU_ID))
                .and(ROLE_MENU_MAPPING.ROLE_ID.eq(ROLE_USER_MAPPING.ROLE_ID))
                .and(ROLE_USER_MAPPING.USER_ID.eq(userId))
                .and(ROLE.ID.eq(ROLE_MENU_MAPPING.ROLE_ID))
                .and(ROLE.STATUS.eq(EnableEnum.ENABLE))
                .groupBy(MENU.ID)
                .list();*/
        return menuMapper.listByUserId(userId);
    }

    public List<Menu> all() {
        return menuMapper.all();
    }
    public Menu get(Long id) {
        return menuMapper.single(id);
    }

    public Boolean exist(String path, Long id) {
        return menuMapper.createLambdaQuery()
                .andEq(Menu::getPath, path)
                .andNotEq(Menu::getId, Query.filterEmpty(id))
                .count() > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(Menu menu) {
        menuMapper.insert(menu);
        if (!menu.getRequiresAuth()) {
            RoleMenuMapping rm = new RoleMenuMapping();
            rm.setMenuId(menu.getId());
            rm.setRoleId(Role.ROLE_ID);
            roleMenuMappingMapper.insert(rm);

        }
    }

    @Transactional(rollbackFor = Exception.class)
    public int update(Menu menu) {
        if (Objects.isNull(menu.getHref())) {
            menu.setHref("");
        }
        if (Objects.isNull(menu.getSingleLayout())) {
            menu.setSingleLayout("");
        }
        return menuMapper.updateById(menu);
    }

    @Transactional(rollbackFor = Exception.class)
    public int delete(Long id) {
        return menuMapper.deleteById(id);
    }
}
