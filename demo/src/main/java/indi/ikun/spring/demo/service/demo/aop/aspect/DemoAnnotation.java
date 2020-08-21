package indi.ikun.spring.demo.service.demo.aop.aspect;

import java.lang.annotation.*;

/**
 * @ClassName DemoAnnotation
 * @Author caddyR
 * @Date 2019-08-29 17:35
 * @Version 1.0
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DemoAnnotation {
}
