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

}
