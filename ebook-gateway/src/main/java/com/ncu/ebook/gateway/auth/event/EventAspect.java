package com.ncu.ebook.gateway.auth.event;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName : EventAspect
 * @Description :
 * @Author : xfakir
 * @Date : 2019-08-17 13:54
 * @Version : 1.0
 */
@Aspect
@Component
public class EventAspect {
    @Autowired
    private EventPublisher publisher;

    @Pointcut("@annotation(com.ncu.ebook.gateway.auth.event.RefreshFilterChain)")
    public void publishEvent() {

    }

    @After("publishEvent()")
    public void doAfter() {
        publisher.publishEvent(new InitFilterChainEvent(new Object()));
    }
}
