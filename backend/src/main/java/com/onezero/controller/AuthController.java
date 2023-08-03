package com.onezero.controller;

import com.onezero.model.Result;
import com.onezero.model.TokenResponse;
import com.onezero.model.UserInfo;
import com.onezero.security.SecurityUser;
import com.onezero.security.TokenService;
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

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    @PostMapping("login")
    public Result<TokenResponse> login(String userName, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName, password, new ArrayList<>());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        return Result.ok(tokenService.createToken(authenticate));
    }

    @PostMapping("token")
    public Result<TokenResponse> token(String refreshToken) {
        Authentication authenticate = jwtAuthenticationProvider.authenticate(new BearerTokenAuthenticationToken(refreshToken));
        return Result.ok(tokenService.createToken(authenticate));
    }

    @GetMapping("getUserInfo")
    public Result<UserInfo> getUserInfo(Authentication auth) {
        if (auth.getPrincipal() instanceof SecurityUser user) {
            UserInfo userInfo = new UserInfo(user);
            userInfo.setUserRole("super");
            return Result.ok(userInfo);
        } else {
         return Result.error("授权异常");
        }
    }
}
