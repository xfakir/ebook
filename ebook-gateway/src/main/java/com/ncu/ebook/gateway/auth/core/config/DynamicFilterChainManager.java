package com.ncu.ebook.gateway.auth.core.config;

import com.ncu.ebook.exception.EbookException;
import com.ncu.ebook.gateway.auth.pojo.UrlFilter;
import com.ncu.ebook.util.StringUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName : DynamicFilterChainManager
 * @Description : 动态filter管理器
 * @Author : xfakir
 * @Date : 2019-08-16 22:46
 * @Version : 1.0
 */
@Service
public class DynamicFilterChainManager {

    private DefaultFilterChainManager defaultFilterChainManager;

    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    private Map<String, NamedFilterList> defaultFilterChains;

    @PostConstruct
    public void init() {

        try {
            AbstractShiroFilter shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            assert shiroFilter != null;
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
                    .getFilterChainResolver();
            defaultFilterChainManager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
        } catch (Exception e) {
            throw new EbookException(500, "shiro init error", e);
        }

        defaultFilterChains = new HashMap<>(defaultFilterChainManager.getFilterChains());



    }

    public void initFilterChains(List<UrlFilter> urlFilters) {


        defaultFilterChainManager.getFilterChains().clear();
        if (defaultFilterChains != null) {
            defaultFilterChainManager.getFilterChains().putAll(defaultFilterChains);
        }
        for (UrlFilter urlFilter : urlFilters) {
            String url = urlFilter.getUrl();
            List<String> filterList = Arrays.asList(urlFilter.getFilterName().split(","));
            for (String filter : filterList) {
                defaultFilterChainManager.addToChain(url, filter);
            }

            if (!StringUtils.isEmptyOrNull(urlFilter.getRoles())) {
                defaultFilterChainManager.addToChain(url, "hasRole", urlFilter.getRoles());
            }
            /*if (!StringUtils.isEmptyOrNull(urlFilter.getPermissions())) {
                defaultFilterChainManager.addToChain(url, "perms", urlFilter.getPermissions());
            }*/
        }
        System.out.println("filter chain refreshed");

    }


}
