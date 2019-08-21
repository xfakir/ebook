package com.ncu.ebook.gateway.auth.core.matcher;

import com.ncu.ebook.config.RedisManager;
import com.ncu.ebook.util.BcryptUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName : EbookCredentialMatcher
 * @Description : 自定义密码匹配器
 * @Author : xfakir
 * @Date : 2019-08-06 19:26
 * @Version : 1.0
 */
public class PasswordMatcher implements CredentialsMatcher {
    private static final String PREFIX = "RETRY:";

    private static final Long RETRY_EXPIRE_TIME = 24*60*60L;


    private RedisManager redisManager;

    public PasswordMatcher(RedisManager redisManager) {
        this.redisManager = redisManager;
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String tokenCredentials = String.valueOf((char[])this.getSubmittedPassword(usernamePasswordToken));
        String infoCredentials = (String) this.getStoredPassword(authenticationInfo);

        Boolean isLoginSuccess = BcryptUtils.decrypt(tokenCredentials, infoCredentials);

        handleRetryCache((String)authenticationToken.getPrincipal(), isLoginSuccess);

        return isLoginSuccess;
    }

    protected Object getSubmittedPassword(AuthenticationToken token) {
        return token != null ? token.getCredentials() : null;
    }

    protected Object getStoredPassword(AuthenticationInfo storedAccountInfo) {

        return storedAccountInfo != null ? storedAccountInfo.getCredentials() : null;
    }

    private void handleRetryCache(String principal, Boolean isLoginSuccess) {
        String key = PREFIX + principal;
        Object object = redisManager.get(key);


        if (isLoginSuccess && object != null) {
            redisManager.del(key);

        }
        if (!isLoginSuccess){
            if (object == null) {
                redisManager.set(key, 0,RETRY_EXPIRE_TIME);
            } else {
                AtomicInteger retryCount = new AtomicInteger((int)object);
                redisManager.set(key, retryCount.incrementAndGet(), RETRY_EXPIRE_TIME);

            }

        }
    }
}
