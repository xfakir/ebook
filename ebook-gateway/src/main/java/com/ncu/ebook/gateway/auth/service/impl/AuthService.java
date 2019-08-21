package com.ncu.ebook.gateway.auth.service.impl;

import com.ncu.ebook.gateway.auth.mapper.FilterMapper;
import com.ncu.ebook.gateway.auth.pojo.UrlFilter;
import com.ncu.ebook.mapper.LocalAuthorizationMapper;
import com.ncu.ebook.pojo.po.user.LocalAuthorization;
import com.ncu.ebook.util.BcryptUtils;
import com.ncu.ebook.util.JwtUtils;
import com.ncu.ebook.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName : AuthroizationService
 * @Description :
 * @Author : xfakir
 * @Date : 2019-08-07 21:04
 * @Version : 1.0
 */
@Service
public class AuthService {

    @Autowired
    private FilterMapper filterMapper;

    @Autowired
    private LocalAuthorizationMapper localAuthorizationMapper;

    public LocalAuthorization getByUsername(String username) {


        return localAuthorizationMapper.getByPrincipal(username);
    }

    public void addRole(Long userId, String role) {
        localAuthorizationMapper.addRole(userId, role);
    }

    /**
     * 生成Jwt
     * @param localAuthorization
     * @return
     */
    public String generateJwt(LocalAuthorization localAuthorization) {
        Map<String, String> claimsMap = new HashMap<>();
        claimsMap.put("principal", localAuthorization.getPrincipal());
        claimsMap.put("roles", localAuthorization.getRoles());
        String jwt = JwtUtils.createJwt(claimsMap);
        System.out.println("jwt: " + jwt);
        return jwt;
    }

    public String generateJwt(String principal) {
        return generateJwt(getByUsername(principal));
    }
}
