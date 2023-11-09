package com.onezero.controller.system;

import com.onezero.domain.PageInfo;
import com.onezero.domain.system.User;
import com.onezero.model.Result;
import com.onezero.model.UserModel;
import com.onezero.service.system.UserService;
import lombok.RequiredArgsConstructor;
import org.beetl.sql.core.page.PageResult;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    @GetMapping("list")
    public List<User> list(UserModel user) {
        return userService.list(user);
    }

    @PostMapping("add")
    public Result<String> add(UserModel user) {
        String DEFAULT_PASSWORD = "soybean123";
        user.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
        userService.add(user);
        return Result.info("用户添加成功");
    }

    @GetMapping("page")
    public PageResult<User> page(PageInfo<User> page, UserModel userModel) {
        return userService.page(page, userModel);
    }

    @PostMapping("update")
    public Result<String> update(UserModel user) {
        userService.update(user);
        return Result.info("用户修改成功");
    }

    @PostMapping("remove")
    public Result<String> remove(Long[] ids) {
        userService.deleteBatch(ids);
        return Result.info("用户删除成功");
    }
}
