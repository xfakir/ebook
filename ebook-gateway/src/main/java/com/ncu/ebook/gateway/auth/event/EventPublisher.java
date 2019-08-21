package com.ncu.ebook.gateway.auth.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * @ClassName : EventPublisher
 * @Description :
 * @Author : xfakir
 * @Date : 2019-08-17 13:36
 * @Version : 1.0
 */
@Component
public class EventPublisher implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    public void publishEvent(ApplicationEvent event) {
        applicationContext.publishEvent(event);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
