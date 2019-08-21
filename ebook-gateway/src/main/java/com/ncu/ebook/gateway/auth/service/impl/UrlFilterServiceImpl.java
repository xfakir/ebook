package com.ncu.ebook.gateway.auth.service.impl;

import com.ncu.ebook.gateway.auth.core.config.DynamicFilterChainManager;
import com.ncu.ebook.gateway.auth.event.RefreshFilterChain;
import com.ncu.ebook.gateway.auth.mapper.FilterMapper;
import com.ncu.ebook.gateway.auth.pojo.UrlFilter;
import com.ncu.ebook.gateway.auth.service.UrlFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @ClassName : UrlFilterServiceImpl
 * @Description :
 * @Author : xfakir
 * @Date : 2019-08-16 19:50
 * @Version : 1.0
 */
@Service
public class UrlFilterServiceImpl implements UrlFilterService {
    @Autowired
    private FilterMapper filterMapper;

    @Autowired
    private DynamicFilterChainManager filterChainManager;

    /**
     * bean加载完后调用
     * 初始化shiro filter
     */
    @PostConstruct
    public void init() {
        List<UrlFilter> allUrlFilter = getAllUrlFilter();
        filterChainManager.initFilterChains(allUrlFilter);
    }


    @Override
    public List<UrlFilter> getAllUrlFilter() {
        List<UrlFilter> urlFilters = filterMapper.getAllUrlFilters();
        System.out.println("urlFilters: " + urlFilters);

        return urlFilters;
    }

    @RefreshFilterChain
    @Override
    public void addUrlFilter(UrlFilter urlFilter) {
        filterMapper.addUrlFilter(urlFilter);
    }

    @RefreshFilterChain
    @Override
    public void deleteUrlFilter(UrlFilter urlFilter) {

    }
}
