package com.ncu.ebook.gateway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = {"com.ncu.ebook"})
@MapperScan("com.ncu.ebook")
public class EbookGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbookGatewayApplication.class, args);
    }

}
