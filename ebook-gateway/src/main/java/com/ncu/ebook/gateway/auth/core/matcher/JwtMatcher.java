package com.ncu.ebook.gateway.auth.core.matcher;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

/**
 * @ClassName : JwtMatcher
 * @Description : jwt比较器
 * @Author : xfakir
 * @Date : 2019-08-21 15:20
 * @Version : 1.0
 */
public class JwtMatcher implements CredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {

        return true;
    }
}
