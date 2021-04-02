#引入方式

1. 引入本模块依赖
    ```groovy
    dependencies {   
        implementation(project(":spring-swagger"))
    }
    ```
1. 在引入的模块application.yml内加入如下配置
    ```yaml
    spring:
      profiles:
         active: swagger
    ```
1. 注入swagger global配置类
   ```java
   @SpringBootApplication(scanBasePackages = "org.bougainvillea.spring")
   ```
1. 定制化本项目swagger文档
    ```java
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
   
   /**
    * @author renqiankun
    */
   @Configuration
   @EnableSwagger2
   public class MySwaggerConfig {
       private ApiInfo demoSpringbootApiInfo(){
        return new ApiInfoBuilder()
                .title("user center")
                .description("用户中心")
                .contact(new Contact("青青子衿 悠悠我心","http://127.0.0.1" , "caddyren@qq.com"))
                .version("1.0.0")
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
    
    ```