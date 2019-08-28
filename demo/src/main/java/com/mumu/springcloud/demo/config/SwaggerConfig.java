package com.mumu.springcloud.demo.config;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 *@Author caddyR
 *@Description //SwaggerConfig
 *@Date 2019-04-25 15:29
 *@Param
 *@return
 **/
@Configuration
@ConditionalOnProperty(prefix = "swagger", name = "enable",havingValue = "true")
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.base-package}")
    String basePackage;
    @Value("${swagger.path-regex}")
    String pathRegex;
    @Value("${swagger.title}")
    String title;
    @Value("${swagger.description}")
    String description;
    @Value("${swagger.version}")
    String version;

    @Bean
    public Docket demoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(Predicates.not(PathSelectors.regex(pathRegex)))
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(title)
                .description(description)
                .version(version)
                .build();
    }
}

