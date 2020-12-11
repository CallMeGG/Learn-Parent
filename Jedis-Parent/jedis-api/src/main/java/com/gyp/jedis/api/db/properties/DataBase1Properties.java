package com.gyp.jedis.api.db.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author GYP
 * @version 1.0
 */
@Component
@Data
public class DataBase1Properties {

    @Value("${spring.datasource.database1.url}")
    private String url;

    @Value("${spring.datasource.database1.username}")
    private String username;

    @Value("${spring.datasource.database1.password}")
    private String password;

    @Value("${spring.datasource.database1.driver-class-name}")
    private String driverClassName;
}