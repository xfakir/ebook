package com.ncu.ebook.gateway.auth.service;

import com.ncu.ebook.gateway.auth.pojo.UrlFilter;

import java.util.List;

/**
 * @InterfaceName : UrlFilterService
 * @Description :
 * @Author : xfakir
 * @Date : 2019-08-16 19:49
 * @Version : 1.0
 */
public interface UrlFilterService {
    List<UrlFilter> getAllUrlFilter();

    void addUrlFilter(UrlFilter urlFilter);

    void deleteUrlFilter(UrlFilter urlFilter);
}
