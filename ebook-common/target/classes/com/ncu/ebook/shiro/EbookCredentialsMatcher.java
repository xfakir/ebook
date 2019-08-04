package com.ncu.ebook.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @ClassName : EbookCredentialsMatcher
 * @Description :
 * @Author : xfakir
 * @Date : 2019-08-01 21:35
 * @Version : 1.0
 */
public class EbookCredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        Object credentials = getCredentials(info);


        return super.doCredentialsMatch(token, info);
    }
}
