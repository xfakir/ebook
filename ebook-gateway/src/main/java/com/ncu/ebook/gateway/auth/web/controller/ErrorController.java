package com.ncu.ebook.gateway.auth.web.controller;

import com.ncu.ebook.exception.EbookException;
import com.ncu.ebook.util.StringUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName : ErrorController
 * @Description : filter中的异常不能由全局异常处理器GlobalExceptionHandler处理，
 * 故该类处理filter中抛出的异常，返回自定义结构，即EbookResult结构
 * @Author : xfakir
 * @Date : 2019-08-21 20:55
 * @Version : 1.0
 */
@RestController
public class ErrorController extends BasicErrorController {
    public ErrorController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    @Override
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = getStatus(request);
        //自定义的错误信息类
        //status.value():错误代码，
        //body.get("message").toString()错误信息
        Map<String, Object> responseMap = new LinkedHashMap<>();
        responseMap.put("status", status.value());
        responseMap.put("msg", body.get("message").toString());
        responseMap.put("data", null);
        //ExcessiveAttemptsException Filter抛出的自定义错误类
        if (!StringUtils.isEmptyOrNull((String) body.get("exception")) && body.get("exception").equals(ExcessiveAttemptsException.class.getName())) {
            body.put("status", HttpStatus.FORBIDDEN.value());
            status = HttpStatus.FORBIDDEN;
        }
        return new ResponseEntity<Map<String, Object>>(responseMap, status);
    }
}
