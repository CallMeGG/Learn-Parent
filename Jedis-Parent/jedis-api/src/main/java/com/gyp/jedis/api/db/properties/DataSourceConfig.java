package com.gyp.jedis.api.db.properties;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author GYP
 * @version 1.0
 */
@Configuration
@Slf4j
public class DataSourceConfig {

    @Autowired
    private DataBase1Properties dataBase1Properties;

    @Autowired(required = false)
    private DataBase2Properties dataBase2Properties;

    @Bean(name = "dataBase1DataSource")
    @Primary
    public DataSource dataBase1DataSource() {
        log.info("dataBase1DataSource初始化----111111");
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(dataBase1Properties.getUrl());
        dataSource.setUsername(dataBase1Properties.getUsername());
        dataSource.setPassword(dataBase1Properties.getPassword());
        dataSource.setDriverClassName(dataBase1Properties.getDriverClassName());
        return dataSource;
    }

    @Bean(name = "dataBase2DataSource")
    @ConditionalOnProperty(value = "db.type", havingValue = "2")
    public DataSource dataBase2DataSource() {

        if (dataBase2Properties == null) {
            return null;
        }
        log.info("dataBase2DataSource初始化----222222");
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(dataBase2Properties.getUrl());
        dataSource.setUsername(dataBase2Properties.getUsername());
        dataSource.setPassword(dataBase2Properties.getPassword());
        dataSource.setDriverClassName(dataBase2Properties.getDriverClassName());
        return dataSource;
    }
}