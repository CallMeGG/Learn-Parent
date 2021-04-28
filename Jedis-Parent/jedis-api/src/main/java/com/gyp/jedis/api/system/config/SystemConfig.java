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

    @Value("${redis.port}")
    private int redisPort;

    @Value("${redis.ip}")
    private String redisIp;

    @Value("${redis.password}")
    private String redisPassword;

    @Value("${redis.maxTotal}")
    private int redisMaxTotal;

    @Value("${redis.maxIdle}")
    private int redisMaxIdle;

    @Value("${redis.maxWaitMillis}")
    private long redisMaxWaitMillis;

    @Value("${redis.testOnBorrow}")
    private boolean redisTestOnBorrow;
}
