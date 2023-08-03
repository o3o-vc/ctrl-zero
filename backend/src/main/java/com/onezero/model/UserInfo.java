package com.onezero.model;

import com.onezero.security.SecurityUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class UserInfo {
    private final SecurityUser user;
    private String userId;
    private String userName;
    @Setter
    @Getter
    private String userRole;

    public String getUserId() {
        return user.user().getId().toString();
    }
    public String getUserName() {
        return user.getUsername();
    }
}
