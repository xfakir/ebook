package com.ncu.ebook.gateway.auth.event;

import com.ncu.ebook.gateway.auth.core.config.DynamicFilterChainManager;
import com.ncu.ebook.gateway.auth.pojo.UrlFilter;
import com.ncu.ebook.gateway.auth.service.UrlFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName : InitFilterChainEventHandler
 * @Description :
 * @Author : xfakir
 * @Date : 2019-08-17 13:29
 * @Version : 1.0
 */
@Component
public class InitFilterChainEventHandler {



    @Autowired
    private DynamicFilterChainManager filterChainManager;

    @EventListener
    public void listener(InitFilterChainEvent initFilterChainEvent) {

        filterChainManager.initFilterChains((List<UrlFilter>) initFilterChainEvent.getSource());
    }
}
