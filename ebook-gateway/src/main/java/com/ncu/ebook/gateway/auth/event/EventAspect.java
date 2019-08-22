package com.ncu.ebook.gateway.auth.event;

import com.ncu.ebook.gateway.auth.service.UrlFilterService;
import com.ncu.ebook.util.SpringBeanUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
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

    private UrlFilterService urlFilterService;

    @Pointcut("@annotation(com.ncu.ebook.gateway.auth.event.RefreshFilterChain)")
    public void publishEvent() {

    }

    @Around("publishEvent()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        urlFilterService = (UrlFilterService) SpringBeanUtils.getObject(joinPoint.getTarget().getClass());
        Object obj = joinPoint.proceed();
        publisher.publishEvent(new InitFilterChainEvent(urlFilterService.getAllUrlFilter()));
        return obj;
    }

}
