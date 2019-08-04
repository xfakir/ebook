package com.ncu.ebook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName : SwaggerConfig
 * @Description :
 * @Author : xfakir
 * @Date : 2019-08-04 13:42
 * @Version : 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("E书")
                .description("E书 API 1.0 操作文档")
                //服务条款网址
                .termsOfServiceUrl("http://www.eshu.com/")
                .version("1.0")
                .contact(new Contact("E书", "http://www.eshu.com/", "eshu@gmail.com"))
                .build();
    }
}
