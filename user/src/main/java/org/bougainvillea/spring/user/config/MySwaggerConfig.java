package org.bougainvillea.spring.user.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class MySwaggerConfig {

    private ApiInfo demoSpringbootApiInfo(){
        return new ApiInfoBuilder()
                .title("user center")
                .description("用户中心")
                .contact(new Contact("天街小雨润如酥","http://127.0.0.1" , "caddyren@qq.com"))
                .version("0.0.1")
                .build();
    }

    @Bean
    public Docket v1Api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("v1")
                .apiInfo(demoSpringbootApiInfo())
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .select()
                .apis(
                        Predicates.or(
                                RequestHandlerSelectors.basePackage("org.bougainvillea.spring.user.v1"),
                                RequestHandlerSelectors.basePackage("org.bougainvillea.spring.user")
                        ))
                .paths(Predicates.not(PathSelectors.regex("/stop")))
                .build();
    }
}
