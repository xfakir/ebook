package com.ncu.ebook.gateway.auth.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName : InitFilterChainEventHandler
 * @Description :
 * @Author : xfakir
 * @Date : 2019-08-17 13:29
 * @Version : 1.0
 */
@Component
public class InitFilterChainEventHandler {

    @EventListener
    public void listener(InitFilterChainEvent initFilterChainEvent) {
        System.out.println("listening");

    }
}
