package indi.ikun.spring.h3bpm.config;


import com.sun.org.apache.xerces.internal.util.HTTPInputSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


/**
 * @TODO // 初始化bean配置
 * @Author:renqiankun
 * @Date:2020-01-08 11:11
 **/
@Configuration
public class BeanConfig {

    /**
      *@TODO //http restTemplate
      *@Author:renqiankun
      *@Date:2020-01-08 11:16
      *@param:
      *@return:
      **/
    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        //单位为ms
        factory.setReadTimeout(60000);
        //单位为ms
        factory.setConnectTimeout(60000);
        return factory;
    }

    /**
     * @TODO //https restTemplate
     * @Author:renqiankun
     * @Date:2020-01-08 11:16
     * @param:
     * @return:
     **/
//    @Bean
    public RestTemplate httpsRestTemplate(HttpsClientRequestFactory factory) {
        RestTemplate httpsRestTemplate=new RestTemplate(factory);
        return httpsRestTemplate;
    }
}
