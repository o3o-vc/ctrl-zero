package com.onezero.service.system;


import com.onezero.domain.PageInfo;
import com.onezero.domain.system.Role;
import com.onezero.domain.system.RoleMenuMapping;
import com.onezero.domain.system.RoleUserMapping;
import com.onezero.mapper.system.RoleMapper;
import com.onezero.mapper.system.RoleMenuMappingMapper;
import com.onezero.mapper.system.RoleUserMappingMapper;
import lombok.RequiredArgsConstructor;
import org.beetl.sql.core.page.PageResult;
import org.beetl.sql.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleMapper roleMapper;
    private final RoleUserMappingMapper roleUserMappingMapper;
    private final RoleMenuMappingMapper roleMenuMappingMapper;

    public PageResult<Role> page(PageInfo<Role> page, Role role) {
        return roleMapper.createLambdaQuery()
                .andEq(Role::getCode, Query.filterEmpty(role.getCode()))
                .page(page);
    }

    public Role get(Long id) {
        return roleMapper.single(id);
    }

    public boolean exist(String code, Long id) {
        return roleMapper.createLambdaQuery()
                .andNotEq(Role::getId, Query.filterEmpty(id))
                .andEq(Role::getCode, code)
                .count() > 0;
    }
    @Transactional(rollbackFor = Exception.class)
    public void add(Role role) {
        roleMapper.insert(role);
    }
    @Transactional(rollbackFor = Exception.class)
    public int update(Role role) {
        return roleMapper.updateById(role);
    }
    @Transactional(rollbackFor = Exception.class)
    public int delete(Long id) {
        return roleMapper.deleteById(id);
    }

    public int deleteUserMappings(Long roleId) {
        return roleUserMappingMapper.createLambdaQuery()
                .andEq(RoleUserMapping::getRoleId, roleId)
                .delete();
    }
    public int deleteMenuMappings(Long roleId) {
        return roleMenuMappingMapper.createLambdaQuery()
                .andEq(RoleMenuMapping::getRoleId, roleId)
                .delete();
    }
    @Transactional(rollbackFor = Exception.class)
    public void addUserMappings(Long roleId, Long[] userIds) {
        deleteUserMappings(roleId);
        if (Objects.nonNull(userIds)) {
            roleUserMappingMapper.insertBatch(
                    Arrays.stream(userIds)
                            .map(
                                    userId -> {
                                        RoleUserMapping ru = new RoleUserMapping();
                                        ru.setRoleId(roleId);
                                        ru.setUserId(userId);
                                        return ru;
                                    }
                            )
                            .toList()
            );
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void addMenuMappings(Long roleId, Long[] menuIds, Long[] markIds) {
        deleteMenuMappings(roleId);
        if (Objects.nonNull(menuIds)) {
            roleMenuMappingMapper.insertBatch(
                    Arrays.stream(menuIds)
                            .map(
                                    menuId -> {
                                        RoleMenuMapping rm = new RoleMenuMapping();
                                        rm.setRoleId(roleId);
                                        rm.setMenuId(menuId);
                                        rm.setIsMark(false);
                                        return rm;
                                    }
                            )
                            .toList()
            );
        }
        if (Objects.nonNull(markIds)) {
            roleMenuMappingMapper.insertBatch(
                    Arrays.stream(markIds)
                            .map(
                                    menuId -> {
                                        RoleMenuMapping rm = new RoleMenuMapping();
                                        rm.setRoleId(roleId);
                                        rm.setMenuId(menuId);
                                        rm.setIsMark(true);
                                        return rm;
                                    }
                            )
                            .toList()
            );
        }
    }

    public List<RoleUserMapping> listUserMapping(Long roleId) {
        return roleUserMappingMapper.createLambdaQuery()
                .andEq(RoleUserMapping::getRoleId, roleId)
                .select();
    }

    public List<RoleMenuMapping> listMenuMapping(Long roleId) {
        return roleMenuMappingMapper.createLambdaQuery()
                .andEq(RoleMenuMapping::getRoleId, roleId)
                .select();
    }
}
