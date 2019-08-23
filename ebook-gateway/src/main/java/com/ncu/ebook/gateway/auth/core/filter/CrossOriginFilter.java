package com.ncu.ebook.gateway.auth.core.filter;

import com.ncu.ebook.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @ClassName : CrossOriginFilter
 * @Description : 跨域filter 目前没用上
 * @Author : xfakir
 * @Date : 2019-08-11 21:55
 * @Version : 1.0
 */
public class CrossOriginFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return StringUtils.equalsIgnoreCase("OPTIONS", ((ShiroHttpServletRequest) servletRequest).getMethod());
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return false;
    }
}
