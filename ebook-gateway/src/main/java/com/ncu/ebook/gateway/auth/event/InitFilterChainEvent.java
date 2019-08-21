package com.ncu.ebook.gateway.auth.event;

import org.springframework.context.ApplicationEvent;

/**
 * @ClassName : InitFilterChainEvent
 * @Description :
 * @Author : xfakir
 * @Date : 2019-08-16 23:09
 * @Version : 1.0
 */
public class InitFilterChainEvent extends ApplicationEvent {
    public InitFilterChainEvent(Object source) {
        super(source);
    }
}
