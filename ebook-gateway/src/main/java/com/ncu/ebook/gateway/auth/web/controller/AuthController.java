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

    @RequestMapping(value = "/auth")
    public EbookResult auth(LocalSignInUser localUser, HttpServletResponse httpServletResponse) {
        //remeber me filter

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(localUser.getUsername(), localUser.getPassword());
        Subject subject = SecurityUtils.getSubject();
        /*try {
            subject.login(usernamePasswordToken);
        } catch (IncorrectCredentialsException e) {
            throw e;
        }*/

        subject.login(usernamePasswordToken);
        String jwtToken = (String)subject.getPrincipal();

        System.out.println("JwtToken" + jwtToken);
        httpServletResponse.setHeader("Authorization", jwtToken);

        return EbookResult.build(200,"success login", null);


    }

    @RequestMapping(value = "/unauth")
    public EbookResult unauth() {
        return EbookResult.build(400,"access denied,unauth", null);
    }

    @RequestMapping(value = "/addRole/{userId}/{roleName}")
    public EbookResult addRole(@PathVariable Long userId, @PathVariable String roleName) {
        authService.addRole(userId, roleName);
        return EbookResult.build(200,"success add", null);
    }
}
