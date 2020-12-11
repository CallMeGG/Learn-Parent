package com.gyp.jedis.api.db.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @author GYP
 * @version 1.0
 */

@Component
@Data
@ConditionalOnProperty(value = "db.type", havingValue = "2")
public class DataBase2Properties {

    @Value("${spring.datasource.database2.url}")
    private String url;

    @Value("${spring.datasource.database2.username}")
    private String username;

    @Value("${spring.datasource.database2.password}")
    private String password;

    @Value("${spring.datasource.database2.driver-class-name}")
    private String driverClassName;
}