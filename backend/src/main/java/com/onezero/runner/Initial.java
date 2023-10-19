package com.onezero.runner;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onezero.Handler;
import com.onezero.domain.PageInfo;
import com.onezero.domain.TestDo;
import com.onezero.domain.system.Menu;
import com.onezero.domain.system.User;
import com.onezero.mapper.TestMapper;
import com.onezero.mapper.system.MenuMapper;
import com.onezero.mapper.system.UserMapper;
import com.onezero.service.system.UserService;
import lombok.RequiredArgsConstructor;
import org.beetl.sql.core.page.DefaultPageRequest;
import org.beetl.sql.core.page.DefaultPageResult;
import org.beetl.sql.core.page.PageResult;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@Component
@RequiredArgsConstructor
public class Initial implements CommandLineRunner {
    private final MenuMapper menuMapper;
    private final TestMapper testMapper;
    private final UserService userService;
    @Override
    public void run(String... args) throws Exception {
        /*ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        List<Map<String, Object>> routes = objectMapper.readValue(Handler.json, new TypeReference<>() {
        });
        List<Menu> groups = new ArrayList<>();
        routes.forEach(first -> groups.add(fillMenu(first)));
        groups.forEach(m -> insertMenu(m, 0L));
        System.out.println(groups);*/
        List<TestDo> all = testMapper.select();
        System.out.println(all.size());
        DefaultPageRequest.of(1, 2);
        PageResult<TestDo> pageResult = testMapper.page(PageInfo.of(1, 2), "test");
        System.out.println(pageResult);
        PageResult<User> page = userService.page(PageInfo.of(1, 2), new User());
        System.out.println(page);
//        userService.deleteBatch(new Long[]{6L});
        /*User user = new User();
        user.setName("abc");
        userService.add(user);
        System.out.println(user);*/
        TestDo testDo = new TestDo();
        testDo.setName("haoa");
        testDo.setMark("bieguan");
        testMapper.testInsert(testDo);
        testMapper.updateById(testDo);
        User byUsername = userService.getByUsername("admin");
        System.out.println(byUsername);
    }

    private void insertMenu(Menu menu, Long parentId) {
        menu.setParentId(parentId);
        menuMapper.insert(menu);
        if (menu.getChildren() != null && ! menu.getChildren().isEmpty()) {
            menu.getChildren().forEach(sub -> insertMenu(sub, menu.getId()));
        }

    }

    public static void main(String[] args) throws Exception {
//        new Initial(null).run();
    }

    private Menu fillMenu(Map<?, ?> target) {
        Menu menu = new Menu();
        menu.setName((String) target.get("name"));
        menu.setPath((String) target.get("path"));
        menu.setComponent((String) target.get("component"));
        //meta
        if (target.get("meta") instanceof Map<?, ?> meta) {
            menu.setIcon((String) meta.get("icon"));
            menu.setOrderNo((Integer) meta.get("order"));
            menu.setTitle((String) meta.get("title"));
            menu.setRequiresAuth((Boolean) meta.get("requiresAuth"));
            menu.setI18nTitle((String) meta.get("i18nTitle"));
            menu.setLocalIcon((String) meta.get("localIcon"));
            menu.setSingleLayout((String) meta.get("singleLayout"));
            menu.setKeepAlive((Boolean) meta.get("keepAlive"));
            menu.setHref((String) meta.get("href"));
        }

        if (target.containsKey("children")) {
            if (target.get("children") instanceof List<?> list) {
                List<Menu> children = new ArrayList<>();
                list.forEach(s -> {
                    if (s instanceof Map<?, ?> sub) {
                        children.add(fillMenu(sub));
                    }
                });
                if (!children.isEmpty()) {
                    menu.setChildren(children);
                }
            }
        }
        return menu;
    }
}
