package com.onezero.security;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import java.util.Collection;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class CzAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
    private final AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();


    /**
     * Determines if the current user is authorized by evaluating if the
     * {@link Authentication} is not anonymous and authenticated.
     * @param authentication the {@link Supplier} of the {@link Authentication} to check
     * @param context the {@link RequestAuthorizationContext} object to check
     * @return an {@link AuthorizationDecision}
     */
    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        Authentication auth = authentication.get();
        boolean isGranted = isGranted(auth);
        // 当前用户的权限信息 比如角色
        Collection<? extends GrantedAuthority> authorities = authentication.get().getAuthorities();
        // 当前请求上下文
        // 我们可以获取携带的参数
        // Map<String, String> variables = context.getVariables();
        // 我们可以获取原始request对象
        HttpServletRequest request = context.getRequest();
        String requestUri = request.getRequestURI();

        // 根据这些信息 和业务写逻辑即可 最终决定是否授权 isGranted 权限验证
        /*if (!SecurityUtil.isAdmin(auth)) {
            if (!authorities.contains(new SimpleGrantedAuthority(requestUri))) {
                isGranted = false;
            }
        }*/
        return new AuthorizationDecision(isGranted);
    }

    private boolean isGranted(Authentication authentication) {
        return authentication != null && isNotAnonymous(authentication) && authentication.isAuthenticated();
    }

    private boolean isNotAnonymous(Authentication authentication) {
        return !this.trustResolver.isAnonymous(authentication);
    }

}
