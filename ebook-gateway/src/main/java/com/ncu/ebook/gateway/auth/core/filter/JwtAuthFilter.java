package com.ncu.ebook.gateway.auth.core.filter;

import com.ncu.ebook.gateway.auth.token.JwtToken;
import com.ncu.ebook.pojo.vo.EbookResult;
import com.ncu.ebook.util.HttpUtils;
import com.ncu.ebook.util.JsonUtils;
import com.ncu.ebook.util.StringMap;
import com.ncu.ebook.util.StringUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static jdk.nashorn.internal.objects.Global.print;

/**
 * @ClassName : JwtAuthFilter
 * @Description : jwt认证filter
 * @Author : xfakir
 * @Date : 2019-08-08 13:35
 * @Version : 1.0
 */
public class JwtAuthFilter extends AccessControlFilter {
    private static final String AUTH_HEADER = "Authorization";


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return null != getSubject(request, response)
                && getSubject(request, response).isAuthenticated();

    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        System.out.println("jwt filter access denied");

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String jwt = getAuthorizationHeader(httpServletRequest);
        if (!StringUtils.isEmptyOrNull(jwt)) {
            JwtToken jwtToken = new JwtToken(jwt, HttpUtils.getIPAddress(httpServletRequest));
            Subject subject = getSubject(servletRequest, servletResponse);
            try {
                subject.login(jwtToken);
                this.onLoginSuccess(servletResponse, subject);
            } catch (AuthenticationException e) {
                throw new AuthenticationException("login failure");
            }

            return true;
        }  else {
            throw new AccountException("login, please");
        }
    }

    /**
     * 每次jwt验证成功后 刷新token并返回
     * @param servletResponse
     * @param subject
     */
    private void onLoginSuccess(ServletResponse servletResponse, Subject subject) {
        System.out.println("login success");
        String jwtToken = (String)subject.getPrincipal();
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setHeader("Authorization", jwtToken);
    }


    private String getAuthorizationHeader(HttpServletRequest request) {
        return request.getHeader(AUTH_HEADER);

    }
}
