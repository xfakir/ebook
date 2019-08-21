package com.ncu.ebook.exception;

import com.ncu.ebook.pojo.vo.EbookResult;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.CredentialException;
import javax.security.auth.login.LoginException;

/**
 * @ClassName : GlobalExceptionHandler
 * @Description :
 * @Author : xfakir
 * @Date : 2019-07-31 14:08
 * @Version : 1.0
 */
@ControllerAdvice(annotations = RestController.class)
@Component
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = EbookException.class)
    public EbookResult ebookExceptionHandler(EbookException e) {
        // e.printStackTrace();
        if (e instanceof ExampleException) {
            //
            //
            return EbookResult.build(500,"error",null);

        }
        return EbookResult.build(500, "unknown error", null);
    }

    @ResponseBody
    @ExceptionHandler(value = ShiroException.class)
    public EbookResult ShiroExceptionHandler(ShiroException e) {
        // e.printStackTrace();
        if (e instanceof IncorrectCredentialsException) {
            //
            //
            return EbookResult.build(500,e.getMessage(),null);

        }
        if (e instanceof AccountException) {
            return EbookResult.build(500, e.getMessage(), null);
        }
        return EbookResult.build(500, "unknown error", null);
    }
}
