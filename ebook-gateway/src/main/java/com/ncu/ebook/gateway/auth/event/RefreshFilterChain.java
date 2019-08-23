package com.ncu.ebook.gateway.auth.event;

import java.lang.annotation.*;

/**
 * 该注解用于每次修改UrlFIlter后，通过aop和事件监听调用InitFilterChainEventHandler的listener方法刷新
 * shiro中UrlFilter
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RefreshFilterChain {
    String event() default "";
}
