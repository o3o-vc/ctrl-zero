/*
package com.onezero.security;

import com.bmht.adv.domain.mvc.UserInfo;
import com.bmht.adv.domain.system.User;
import com.bmht.adv.security.model.SecurityUserDetails;
import com.bmht.adv.util.FillUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

*/
/**
 * @author: FenCho
 * @description:  当前登录上下文信息工具类
 * @date: 2022/11/26 15:23
 *//*

public interface SecurityUtil {
    static UserInfo getUserInfo() {
        SecurityUserDetails details = getUserDetails();
        return new UserInfo(details.getId().toString(), details.getUsername(), details.getName());
    }

    static SecurityContext getContext() {
        return SecurityContextHolder.getContext();
    }
    static Authentication getAuthentication() {
        return getContext().getAuthentication();
    }

    static SecurityUserDetails getUserDetails() {
        return getUserDetails(getAuthentication());
    }

    static SecurityUserDetails getUserDetails(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof SecurityUserDetails securityUserDetails) {
            return securityUserDetails;
        } else {
            throw new RuntimeException("请确保UserDetailsService的实现是基于SecurityUserDetails");
        }
    }

    static Long getUserId() {
        return getUserDetails().getId();
    }

    static boolean isAdmin() {
        return FillUtil.getAdmId().equals(getUserDetails().getId());
    }

    static boolean isAdmin(User user) {
        return FillUtil.getAdmId().equals(user.getId());
    }

    static boolean isAdmin(Authentication authentication) {
        return FillUtil.getAdmId().equals(getUserDetails(authentication).getId());
    }

}
*/
