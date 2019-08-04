package com.ncu.ebook.config;

import com.ncu.ebook.interceptor.LogInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName : InterceptorConfig
 * @Description :
 * @Author : xfakir
 * @Date : 2019-07-31 13:27
 * @Version : 1.0
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }
}
