package com.ncu.ebook.gateway.auth.core.config;

import com.ncu.ebook.config.RedisManager;
import com.ncu.ebook.gateway.auth.core.factory.StatelessSubjectFactory;
import com.ncu.ebook.gateway.auth.core.filter.JwtAuthFilter;
import com.ncu.ebook.gateway.auth.core.filter.PasswordAuthFilter;
import com.ncu.ebook.gateway.auth.core.filter.RoleFilter;
import com.ncu.ebook.gateway.auth.core.matcher.JwtMatcher;
import com.ncu.ebook.gateway.auth.core.matcher.PasswordMatcher;
import com.ncu.ebook.gateway.auth.core.realm.JwtRealm;
import com.ncu.ebook.gateway.auth.core.realm.PasswordRealm;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSessionStorageEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName : ShiroConfig
 * @Description : shiro 配置文件
 * @Author : xfakir
 * @Date : 2019-08-06 19:19
 * @Version : 1.0
 */
@Configuration
public class ShiroConfig {
    @Autowired
    private RedisManager redisManager;



    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager());

        shiroFilterFactoryBean.setLoginUrl("/api/v1/auth");
        shiroFilterFactoryBean.setUnauthorizedUrl("/api/v1/unauth");
        Map<String, Filter> filterMap = shiroFilterFactoryBean.getFilters();
        filterMap.put("password", new PasswordAuthFilter(redisManager));
        filterMap.put("hasRole", new RoleFilter());
        filterMap.put("jwt", new JwtAuthFilter());
        shiroFilterFactoryBean.setFilters(filterMap);


        return shiroFilterFactoryBean;
    }


    @Bean
    public Authenticator authenticator() {
        EbookModularRealmAuthenticator modularRealmAuthenticator = new EbookModularRealmAuthenticator();
        //modularRealmAuthenticator.setRealms();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticator;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setSubjectFactory(statelessSubjectFactory());
        defaultSecurityManager.setAuthenticator(authenticator());
        List<Realm> realms = new ArrayList<>();
        realms.add(passwordRealm());
        realms.add(jwtRealm());
        defaultSecurityManager.setRealms(realms);
        return defaultSecurityManager;
    }

    @Bean
    public StatelessSubjectFactory statelessSubjectFactory() {
        return new StatelessSubjectFactory();
    }

    @Bean
    protected SessionStorageEvaluator sessionStorageEvaluator(){
        DefaultWebSessionStorageEvaluator sessionStorageEvaluator = new DefaultWebSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        return sessionStorageEvaluator;
    }

    public PasswordMatcher ebookCredentialMatcher() {
        return new PasswordMatcher(redisManager);
    }

    @Bean
    public PasswordRealm passwordRealm() {
        PasswordRealm passwordRealm = new PasswordRealm();
        passwordRealm.setCredentialsMatcher(ebookCredentialMatcher());
        return passwordRealm;
    }

    @Bean
    public JwtMatcher jwtMatcher() {
        return new JwtMatcher();
    }

    @Bean
    public JwtRealm jwtRealm() {
        JwtRealm jwtRealm = new JwtRealm();
        jwtRealm.setCredentialsMatcher(jwtMatcher());
        return jwtRealm;
    }




}

