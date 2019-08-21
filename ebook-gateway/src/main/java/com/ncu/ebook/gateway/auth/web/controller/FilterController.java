package com.ncu.ebook.gateway.auth.web.controller;

import com.ncu.ebook.gateway.auth.pojo.UrlFilter;
import com.ncu.ebook.gateway.auth.service.impl.AuthService;
import com.ncu.ebook.gateway.auth.service.impl.UrlFilterServiceImpl;
import com.ncu.ebook.pojo.vo.EbookResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : FilterController
 * @Description :
 * @Author : xfakir
 * @Date : 2019-08-15 21:26
 * @Version : 1.0
 */
@RestController
@RequestMapping(value = "/filter")
public class FilterController {

    @Autowired
    private UrlFilterServiceImpl urlFilterService;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/add")
    public EbookResult addUrlFilter(UrlFilter urlFilter) {
        urlFilterService.addUrlFilter(urlFilter);

        return new EbookResult(200, "succeed", urlFilter);
    }

    @RequestMapping(value = "/all")
    public EbookResult getAllUrlFilter() {
        //filterService.test();


        return new EbookResult(200, "succeed", urlFilterService.getAllUrlFilter());
    }
}
