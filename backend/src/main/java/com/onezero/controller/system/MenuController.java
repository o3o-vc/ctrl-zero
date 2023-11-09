package com.onezero.controller.system;

import com.onezero.core.util.TreeUtil;
import com.onezero.domain.system.Menu;
import com.onezero.model.Result;
import com.onezero.service.system.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("menu")
public class MenuController {

    private final MenuService menuService;

    @GetMapping("list")
    public List<Menu> list() {
        return TreeUtil.listToTree(menuService.all(), m -> {
            if (Objects.isNull(m.getOrderNo())) m.setOrderNo(0);
            if (Objects.isNull(m.getChildren())) {
                m.setChildren(List.of());
            }
        });
    }

    @PostMapping("add")
    public Result<String> add(Menu menu) {
        if (menuService.exist(menu.getPath(), null)) {
            return Result.warn("该路径菜单已经存在");
        }
        menuService.add(menu);
        return Result.info("菜单添加成功");
    }

    @PostMapping("update")
    public Result<String> update(Menu menu) {
        if (menuService.exist(menu.getPath(), menu.getId())) {
            return Result.warn("该路径菜单已经存在");
        }
        menuService.update(menu);
        return Result.info("菜单修改成功");
    }

    @PostMapping("remove")
    public Result<String> remove(Long id) {
        menuService.delete(id);
        return Result.info("菜单删除成功");
    }
}
