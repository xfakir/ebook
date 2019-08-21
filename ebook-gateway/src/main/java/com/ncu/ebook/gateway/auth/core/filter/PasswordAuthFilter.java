package com.ncu.ebook.gateway.auth.core.filter;

import com.ncu.ebook.config.RedisManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName : PasswordFilter
 * @Description : 密码登录filter
 * @Author : xfakir
 * @Date : 2019-08-09 21:32
 * @Version : 1.0
 */
@Slf4j
public class PasswordAuthFilter extends FormAuthenticationFilter {
    private static final String PREFIX = "RETRY:";

    private static final Long RETRY_EXPIRE_TIME = 24*60*60L;


    private RedisManager redisManager;


    public PasswordAuthFilter(RedisManager redisManager) {
        this.redisManager = redisManager;
    }

    /*@Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        request.setAttribute(DefaultSubjectContext.SESSION_CREATION_ENABLED, Boolean.FALSE);
        return true;
    }*/

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String username = httpServletRequest.getParameter("username");


        String key = PREFIX + username;
        Object object = redisManager.get(key);

        if (object != null) {
            Integer retryCount = (Integer) object;
            if (retryCount > 5) {

                throw new ExcessiveAttemptsException("account locked");
            }
        }

        return true;
    }


    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String principal = httpServletRequest.getParameter("username");
        String credential = httpServletRequest.getParameter("password");
        return this.createToken(principal, credential, servletRequest, servletResponse);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return false;
    }

   /* @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        UserSubjectWrapper userSubjectWrapper = (UserSubjectWrapper)subject.getPrincipal();

        Map<String, String> claimsMap = new HashMap<>();
        claimsMap.put("principal", userSubjectWrapper.getPrincipal());
        claimsMap.put("roles", StringUtils.join(userSubjectWrapper.getRoleList().toArray(), ","));

        String jwtToken = JwtUtils.createJwt(claimsMap);
        System.out.println("JwtToken" + jwtToken);
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Authorization", jwtToken);

        handleRetryCache((String)token.getPrincipal(), true);

        return false;
    }*/

    /*@Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        String principal = (String) token.getPrincipal();
        System.out.println("login failure");
        handleRetryCache(principal, false);

        return true;
    }*/

    /*private void handleRetryCache(String principal, Boolean isLoginSuccess) {
        String key = PREFIX + principal;
        Object object = redisManager.get(key);


        if (isLoginSuccess && object != null) {
            redisManager.del(key);

        }
        if (!isLoginSuccess){
            if (object == null) {
                redisManager.set(key, 0,RETRY_EXPIRE_TIME);
            } else {
                AtomicInteger retryCount = (AtomicInteger) object;
                redisManager.set(key, retryCount.incrementAndGet(), RETRY_EXPIRE_TIME);

            }

        }
    }*/

}
