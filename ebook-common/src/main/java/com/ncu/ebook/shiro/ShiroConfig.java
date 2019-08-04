package com.ncu.ebook.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName : ShiroConfig
 * @Description :
 * @Author : xfakir
 * @Date : 2019-08-01 21:14
 * @Version : 1.0
 */

public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        /*ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        shiroFilterFactoryBean.setLoginUrl("/signIn");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/signOut", "logout");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/signIn", "anon");
        filterChainDefinitionMap.put("/signUp", "anon");
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;*/
        return null;
    }
}
