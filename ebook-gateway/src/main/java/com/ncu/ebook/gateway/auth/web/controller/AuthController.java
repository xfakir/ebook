package com.ncu.ebook.gateway.auth.web.controller;

import com.ncu.ebook.gateway.auth.pojo.LocalSignInUser;
import com.ncu.ebook.gateway.auth.service.impl.AuthService;
import com.ncu.ebook.pojo.vo.EbookResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


/**
 * @ClassName : AuthController
 * @Description :
 * @Author : xfakir
 * @Date : 2019-08-05 20:11
 * @Version : 1.0
 */
@RestController
@RequestMapping(value = "/api/v1")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * 登录
     * @param localUser
     * @param httpServletResponse
     * @return
     */
    @RequestMapping(value = "/auth")
    public EbookResult auth(LocalSignInUser localUser, HttpServletResponse httpServletResponse) {


        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(localUser.getUsername(), localUser.getPassword());
        Subject subject = SecurityUtils.getSubject();

        subject.login(usernamePasswordToken);
        String jwtToken = (String)subject.getPrincipal();

        System.out.println("JwtToken" + jwtToken);
        httpServletResponse.setHeader("Authorization", jwtToken);

        return EbookResult.build(200,"success login", null);


    }

    /**
     * 给User添加Role
     * @param userId
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/addRole/{userId}/{roleId}")
    public EbookResult addRole(@PathVariable Long userId, @PathVariable Integer roleId) {
        authService.addRole(userId, roleId);
        return EbookResult.build(200,"success add", null);
    }
}
