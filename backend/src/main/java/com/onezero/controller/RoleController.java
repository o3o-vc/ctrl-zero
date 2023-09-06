package com.onezero.controller;

import com.mybatisflex.core.paginate.Page;
import com.onezero.domain.system.Role;
import com.onezero.domain.system.RoleMenuMapping;
import com.onezero.domain.system.RoleUserMapping;
import com.onezero.model.Result;
import com.onezero.security.access.CmdInfo;
import com.onezero.security.access.annotation.Cmd;
import com.onezero.security.access.annotation.CmdHandler;
import com.onezero.security.access.annotation.Group;
import com.onezero.service.system.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("role")
@RequiredArgsConstructor
@Group("角色管理")
public class RoleController {
    private final RoleService roleService;
    private final CmdHandler cmdHandler;


    @Cmd("分页查询")
    @GetMapping("page")
    public Page<Role> page(Page<Role> page, Role role) {
        return roleService.page(page, role);
    }
    @PostMapping("add")
    public Result<String> add(Role role) {
        if (roleService.exist(role.getCode(), null)) {
            return Result.warn("该编码角色已经存在");
        }
        roleService.add(role);
        return Result.info("角色添加成功");
    }
    @PostMapping("update")
    public Result<String> update(Role role) {
        if (roleService.exist(role.getCode(), role.getId())) {
            return Result.warn("该编码角色已经存在");
        }
        roleService.update(role);
        return Result.info("角色修改成功");
    }
    @PostMapping("remove")
    public Result<String> remove(Long id) {
        roleService.delete(id);
        return Result.info("角色删除成功");
    }

    @GetMapping("listUserMapping")
    public List<RoleUserMapping> listUserMapping(Long roleId) {
        return roleService.listUserMapping(roleId);
    }

    @GetMapping("listMenuMapping")
    public List<RoleMenuMapping> listMenuMapping(Long roleId) {
        return roleService.listMenuMapping(roleId);
    }

    @PostMapping("addUserMappings")
    public Result<String> addUserMappings(Long roleId, Long[] userIds) {
        roleService.addUserMappings(roleId, userIds);
        return Result.info("用户绑定成功");
    }
    @PostMapping("addMenuMappings")
    public Result<String> addMenuMappings(Long roleId, Long[] menuIds, Long[] markIds) {
        roleService.addMenuMappings(roleId, menuIds, markIds);
        return Result.info("菜单绑定成功");
    }

    @GetMapping("listCmdInfo")
    @Cmd(value = "获取所有命令", logged = true)
    public Set<CmdInfo> listCmdInfo() {
        return (Set<CmdInfo>) cmdHandler.getAll();
    }
}
