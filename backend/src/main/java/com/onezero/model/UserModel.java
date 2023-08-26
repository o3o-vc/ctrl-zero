package com.onezero.model;

import com.onezero.domain.system.User;

public class UserModel extends User {
    private String userName;
    private String userStatus;

    public String getUserName() {
        return this.getUsername();
    }
    public void setUserName(String userName) {
        this.setUsername(userName);
    }

    public String getUserStatus() {
        return this.getStatus();
    }

    public void setUserStatus(String userStatus) {
        this.setStatus(userStatus);
    }
}
