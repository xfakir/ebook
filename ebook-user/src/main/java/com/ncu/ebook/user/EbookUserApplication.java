package com.ncu.ebook.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.ncu.ebook"})
public class EbookUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbookUserApplication.class, args);
    }

}
