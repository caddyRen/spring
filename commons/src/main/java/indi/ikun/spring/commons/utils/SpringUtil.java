package indi.ikun.spring.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.*;
import org.springframework.stereotype.Component;

/**
 * @ClassName SpringUtil
 * @Description 获取springboot注入的Bean工具类
 * @Author caddyR
 * @Date 2019-07-16 11:14
 * @Version 1.0
 **/
@Component
@Slf4j
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * Set the ApplicationContext that this object runs in.
     * Normally this call will be used to initialize the object.
     * <p>Invoked after population of normal bean properties but before an init callback such
     * as {@link InitializingBean#afterPropertiesSet()}
     * or a custom init-method. Invoked after {@link ResourceLoaderAware#setResourceLoader},
     * {@link ApplicationEventPublisherAware#setApplicationEventPublisher} and
     * {@link MessageSourceAware}, if applicable.
     *
     * @param applicationContext the ApplicationContext object to be used by this object
     * @throws ApplicationContextException in case of context initialization errors
     * @throws BeansException              if thrown by application context methods
     * @see BeanInitializationException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringUtil.applicationContext==null){
            SpringUtil.applicationContext=applicationContext;
        }
        log.info("###############################################################");
        log.info("ApplicatinContext配置成功，可通过SpringUtil.getAppContent()获取");
        log.info("##############################################################");

    }
    /**
     *@Author caddyR
     *@Description //获取ApplicationContext
     *@Date 2019-07-16 11:25
     *@Param []
     *@return org.springframework.context.ApplicationContext
    **/
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
    /**
     *@Author caddyR
     *@Description //通过name获取Bean
     *@Date 2019-07-16 11:28
     *@Param [name]
     *@return java.lang.Object
    **/
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }
    /**
     *@Author caddyR
     *@Description //通过 class获取Bean
     *@Date 2019-07-16 11:28
     *@Param [clazz]
     *@return T
    **/
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }
    /**
     *@Author caddyR
     *@Description //通过name和class获取Bean
     *@Date 2019-07-16 11:28
     *@Param [name, clazz]
     *@return T
    **/
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name,clazz);
    }


}
