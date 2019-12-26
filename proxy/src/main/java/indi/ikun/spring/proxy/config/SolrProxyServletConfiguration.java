package indi.ikun.spring.proxy.config;

import indi.ikun.spring.proxy.filter.ApiRequestLoggingFilter;
import indi.ikun.spring.proxy.utils.ProxyServlet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * @author renqiankun
 */
@Configuration
public class SolrProxyServletConfiguration implements EnvironmentAware {

    @Bean
    @Order(0)
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new ProxyServlet(), propertyResolver.getProperty("servlet_url"));
        servletRegistrationBean.addInitParameter("targetUri", propertyResolver.getProperty("target_url"));
        servletRegistrationBean.addInitParameter(ProxyServlet.P_LOG, propertyResolver.getProperty("logging_enabled", "true"));
        return servletRegistrationBean;
    }
    private RelaxedPropertyResolver propertyResolver;
    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, "proxy.solr.");
    }

    @Bean
    @ConditionalOnMissingBean(name = "requestLoggingFilter")
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new ApiRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setIncludeHeaders(true);
        loggingFilter.setMaxPayloadLength(1024 * 1024 * 2);
        loggingFilter.setBeforeMessagePrefix("接收请求 [");
        loggingFilter.setBeforeMessageSuffix("]");
        loggingFilter.setAfterMessagePrefix("结束请求 [");
        loggingFilter.setAfterMessageSuffix("]");
        return loggingFilter;
    }
}
