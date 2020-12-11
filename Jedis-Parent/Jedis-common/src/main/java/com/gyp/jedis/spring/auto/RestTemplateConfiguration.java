package com.gyp.jedis.spring.auto;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * Description:
 *
 * @author GYP
 * @version 1.0
 */
@Configuration
public class RestTemplateConfiguration {

    @Bean(value = "httpRestTemplate")
    public RestTemplate httpRestTemplate() {
        Duration connectTimeoutDuration = Duration.ofMillis(20000);
        Duration socketTimeoutDuration = Duration.ofMillis(2000);

        return new RestTemplateBuilder()
                .setConnectTimeout(socketTimeoutDuration)
                .setReadTimeout(connectTimeoutDuration)
                .build();
    }
}
