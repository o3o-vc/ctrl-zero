package com.onezero.security;

import com.onezero.security.access.annotation.CmdHandler;
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

    private final CmdHandler cmdHandler;


    /**
     * Determines if the current user is authorized by evaluating if the
     * {@link Authentication} is not anonymous and authenticated.
     * @param authentication the {@link Supplier} of the {@link Authentication} to check
     * @param context the {@link RequestAuthorizationContext} object to check
     * @return an {@link AuthorizationDecision}
     */
    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        // 当前请求上下文
        // 我们可以获取携带的参数
        // Map<String, String> variables = context.getVariables();
        // 我们可以获取原始request对象
        HttpServletRequest request = context.getRequest();
        String requestUri = request.getRequestURI();
        if (isLogged(requestUri)) {
            return new AuthorizationDecision(true);
        }
        Authentication auth = authentication.get();
        boolean isGranted = isGranted(auth);
        if (!isPermitted(requestUri) && !SecurityUtil.isAdmin(auth)) {
            // 当前用户的权限信息 比如角色
            Collection<? extends GrantedAuthority> authorities = authentication.get().getAuthorities();
            if (!authorities.contains(new SimpleGrantedAuthority(requestUri))) {
                isGranted = false;
            }
        }
        return new AuthorizationDecision(isGranted);
    }

    private boolean isGranted(Authentication authentication) {
        return authentication != null && isNotAnonymous(authentication) && authentication.isAuthenticated();
    }

    private boolean isLogged(String uri) {
        return cmdHandler.getLogged().contains(uri);
    }
    private boolean isPermitted(String uri) {
        return cmdHandler.getPermitted().contains(uri);
    }

    private boolean isNotAnonymous(Authentication authentication) {
        return !this.trustResolver.isAnonymous(authentication);
    }

}
