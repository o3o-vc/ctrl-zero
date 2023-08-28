package com.onezero.controller;

import com.onezero.core.util.TreeUtil;
import com.onezero.domain.system.Menu;
import com.onezero.model.Route;
import com.onezero.model.TokenResponse;
import com.onezero.model.UserInfo;
import com.onezero.security.SecurityUser;
import com.onezero.security.TokenService;
import com.onezero.service.system.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final MenuService menuService;
    @PostMapping("login")
    public TokenResponse login(String userName, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName, password, new ArrayList<>());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        return tokenService.createToken(authenticate);
    }

    @PostMapping("token")
    public TokenResponse token(String refreshToken) {
        Authentication authenticate = jwtAuthenticationProvider.authenticate(new BearerTokenAuthenticationToken(refreshToken));
        return tokenService.createToken(authenticate);
    }

    @GetMapping("getUserInfo")
    public UserInfo getUserInfo(Authentication auth) {
        if (auth.getPrincipal() instanceof SecurityUser user) {
            UserInfo userInfo = new UserInfo(user);
            userInfo.setUserRole("super");
            return userInfo;
        } else {
         throw new RuntimeException("授权异常");
        }
    }
    @PostMapping("getUserRoutes")
    public Map<String, Object> getUserRoutes() {
        List<Menu> menus = menuService.all();
        List<Route> routes = TreeUtil.listToTree(menus, menu -> {
            Route route = new Route();
            route.setId(menu.getId());
            route.setName(menu.getName());
            route.setPath(menu.getPath());
            route.setComponent(menu.getComponent());
            route.setParentId(menu.getParentId());
            route.setOrderNo(Objects.isNull(menu.getOrderNo()) ? 0 : menu.getOrderNo());

            Route.Meta meta = new Route.Meta();
            meta.setIcon(menu.getIcon());
            meta.setOrder(route.getOrderNo());
            meta.setTitle(menu.getTitle());
            meta.setRequiresAuth(menu.getRequiresAuth());
            meta.setI18nTitle(menu.getI18nTitle());
            meta.setLocalIcon(menu.getLocalIcon());
            meta.setSingleLayout(menu.getSingleLayout());
            meta.setKeepAlive(menu.getKeepAlive());
            meta.setHref(menu.getHref());
            route.setMeta(meta);
            return route;
        });
        return Map.of("home", "dashboard_analysis", "routes", routes);
    }
}
