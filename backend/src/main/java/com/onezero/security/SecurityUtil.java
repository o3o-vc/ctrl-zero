package com.onezero.security;


import com.onezero.model.UserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/*
 * @author: FenCho
 * @description:  当前登录上下文信息工具类
 * @date: 2022/11/26 15:23
 */

public interface SecurityUtil {
    Long ADMIN_ID = 1L;
    static UserInfo getUserInfo() {
        SecurityUser securityUser = getUserDetails();
        return new UserInfo(securityUser);
    }

    static SecurityContext getContext() {
        return SecurityContextHolder.getContext();
    }
    static Authentication getAuthentication() {
        return getContext().getAuthentication();
    }

    static SecurityUser getUserDetails() {
        return getUserDetails(getAuthentication());
    }

    static SecurityUser getUserDetails(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof SecurityUser securityUserDetails) {
            return securityUserDetails;
        } else {
            throw new RuntimeException("请确保UserDetailsService的实现是基于SecurityUserDetails");
        }
    }

    static Long getUserId() {
        return getUserDetails().user().getId();
    }

    static boolean isAdmin() {
        return ADMIN_ID == getUserId();
    }

/*    static boolean isAdmin(User user) {
        return FillUtil.getAdmId().equals(user.getId());
    }*/

    static boolean isAdmin(Authentication authentication) {
        return ADMIN_ID == getUserDetails(authentication).user().getId();
    }

}
