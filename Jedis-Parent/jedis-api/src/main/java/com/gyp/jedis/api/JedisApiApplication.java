package com.gyp.jedis.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.gyp.jedis.api", "com.gyp.jedis.feign.api"})
public class JedisApiApplication {


    public static void main(String[] args) {


        ArrayList<Object> objects = new ArrayList<>();

        List<Object> list = Collections.unmodifiableList(objects);

        SpringApplication.run(JedisApiApplication.class, args);


        //RedisPoolsUtil.getSingleton().doTest();


        // 插入测试数据
        // DbService bean = ApplicationUtil.getBean(DbService.class);
        // bean.doMulInsert(10000000);

    }

}
