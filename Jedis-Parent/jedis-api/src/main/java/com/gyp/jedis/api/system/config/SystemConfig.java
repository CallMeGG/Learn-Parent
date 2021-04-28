package com.gyp.jedis.api.system.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Title: SystemConfig
 * date 2019/11/28 13:36
 * Company: GYP
 *
 * @author GYP
 * @version 1.0
 */
@Data
@Accessors(chain = true)
@Component
public class SystemConfig {

    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.host}")
    private String redisIp;

    @Value("${spring.redis.password}")
    private String redisPassword;

}
