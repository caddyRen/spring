package indi.ikun.spring.configserver;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClientImpl;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * @ClassName EurekaClientHttpsCfg
 * @Description TODO
 * @Author caddyR
 * @Date 2019-05-13 15:08
 * @Version 1.0
 **/
//@Configuration
public class EurekaClientHttpsCfg {
    @Value("${server.ssl.test}")
    String keyStoreFileName;

    @Value("${server.ssl.key-store-password}")
    String keyStorePassword;

    @Bean
    public DiscoveryClient.DiscoveryClientOptionalArgs discoveryClientOptionalArgs() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        EurekaJerseyClientImpl.EurekaJerseyClientBuilder builder = new EurekaJerseyClientImpl.EurekaJerseyClientBuilder();
        builder.withClientName("configServer");
        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(
                        this.getClass().getClassLoader().getResource(keyStoreFileName),keyStorePassword.toCharArray()
                )
                .build();
        builder.withCustomSSL(sslContext);
        builder.withMaxTotalConnections(10);
        builder.withMaxConnectionsPerHost(10);

        DiscoveryClient.DiscoveryClientOptionalArgs args = new DiscoveryClient.DiscoveryClientOptionalArgs();
        args.setEurekaJerseyClient(builder.build());
        return args;
    }
}
