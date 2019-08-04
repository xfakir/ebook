package com.ncu.ebook.interceptor;

import com.ncu.ebook.util.DateUtils;
import com.ncu.ebook.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName : LogInterceptor
 * @Description :
 * @Author : xfakir
 * @Date : 2019-07-31 13:32
 * @Version : 1.0
 */
@Slf4j
public class LogInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = HttpUtils.getIPAddress(request);

        String path = request.getServletPath();

        String now = DateUtils.now();

        log.info("IP: {} , URL : {} , DATE : {}", ip, path, now);

        return super.preHandle(request, response, handler);
    }
}
