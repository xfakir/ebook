package com.ncu.ebook.gateway.auth.event;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RefreshFilterChain {
    String event() default "";
}
