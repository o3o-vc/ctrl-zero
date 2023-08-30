package com.onezero.service.system;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.row.Db;
import com.onezero.domain.system.Role;
import com.onezero.domain.system.RoleMenuMapping;
import com.onezero.domain.system.RoleUserMapping;
import com.onezero.domain.system.table.RoleMenuMappingTableDef;
import com.onezero.domain.system.table.RoleTableDef;
import com.onezero.domain.system.table.RoleUserMappingTableDef;
import com.onezero.mapper.system.RoleMapper;
import com.onezero.mapper.system.RoleMenuMappingMapper;
import com.onezero.mapper.system.RoleUserMappingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.onezero.domain.system.table.RoleMenuMappingTableDef.ROLE_MENU_MAPPING;
import static com.onezero.domain.system.table.RoleTableDef.ROLE;
import static com.onezero.domain.system.table.RoleUserMappingTableDef.ROLE_USER_MAPPING;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleMapper roleMapper;
    private final RoleUserMappingMapper roleUserMappingMapper;
    private final RoleMenuMappingMapper roleMenuMappingMapper;

    public Page<Role> page(Page<Role> page, Role role) {
        QueryWrapper queryWrapper = QueryWrapper.create(role);
        return roleMapper.paginate(page, queryWrapper);
    }

    public Role get(Long id) {
        return roleMapper.selectOneById(id);
    }

    public boolean exist(String code, Long id) {
        QueryCondition condition = ROLE.CODE.eq(code)
                .and(ROLE.ID.ne(id, Objects::nonNull));
        return roleMapper.selectCountByCondition(condition) > 0;
    }
    @Transactional(rollbackFor = Exception.class)
    public int add(Role role) {
        return roleMapper.insert(role);
    }
    @Transactional(rollbackFor = Exception.class)
    public int update(Role role) {
        return roleMapper.update(role);
    }
    @Transactional(rollbackFor = Exception.class)
    public int delete(Long id) {
        return roleMapper.deleteById(id);
    }

    public int deleteUserMappings(Long roleId) {
        QueryCondition condition = ROLE_USER_MAPPING.ROLE_ID.eq(roleId);
        return roleUserMappingMapper.deleteByCondition(condition);
    }
    public int deleteMenuMappings(Long roleId) {
        QueryCondition condition = ROLE_MENU_MAPPING.ROLE_ID.eq(roleId);
        return roleMenuMappingMapper.deleteByCondition(condition);
    }
    @Transactional(rollbackFor = Exception.class)
    public int addUserMappings(Long roleId, Long[] userIds) {
        deleteUserMappings(roleId);
        if (Objects.nonNull(userIds)) {
            return roleUserMappingMapper.insertBatch(
                    Arrays.stream(userIds)
                            .map(
                                    userId -> RoleUserMapping.create()
                                            .setRoleId(roleId)
                                            .setUserId(userId)
                            )
                            .toList()
            );
        }
        return 0;
    }

    public int addMenuMappings(Long roleId, Long[] menuIds, Long[] markIds) {
        deleteMenuMappings(roleId);
        int count = 0;
        if (Objects.nonNull(menuIds)) {
            count += roleMenuMappingMapper.insertBatch(
                    Arrays.stream(menuIds)
                            .map(
                                    menuId -> RoleMenuMapping
                                            .create()
                                            .setRoleId(roleId)
                                            .setMenuId(menuId)
                                            .setIsMark(false)
                            )
                            .toList()
            );
        }
        if (Objects.nonNull(markIds)) {
            count += roleMenuMappingMapper.insertBatch(
                    Arrays.stream(markIds)
                            .map(
                                    menuId -> RoleMenuMapping
                                            .create()
                                            .setRoleId(roleId)
                                            .setMenuId(menuId)
                                            .setIsMark(true)
                            )
                            .toList()
            );
        }
        return count;
    }

    public List<RoleUserMapping> listUserMapping(Long roleId) {
        return roleUserMappingMapper.selectListByCondition(ROLE_USER_MAPPING.ROLE_ID.eq(roleId));
    }

    public List<RoleMenuMapping> listMenuMapping(Long roleId) {
        return roleMenuMappingMapper.selectListByCondition(
                ROLE_MENU_MAPPING.ROLE_ID.eq(roleId)
                        .and(ROLE_MENU_MAPPING.IS_MARK.eq(false))
        );
    }
}
