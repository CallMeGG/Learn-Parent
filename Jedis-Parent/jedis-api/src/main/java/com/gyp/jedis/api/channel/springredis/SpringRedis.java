package com.gyp.jedis.api.channel.springredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Title: SpringRedis
 * date 2021/4/28 9:55
 *
 * @author GYP
 * @version 1.0
 */
@Service
public class SpringRedis {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    public void test(){
        redisTemplate.opsForValue().set("test123","value321");

        Object test123 = redisTemplate.opsForValue().get("test123");
        System.out.println(test123);

    }
}
