package com.gyp.jedis.api.controller;

import com.alibaba.fastjson.JSON;
import com.gyp.jedis.ResultCreator;
import com.gyp.jedis.api.channel.jedis.RedisPoolsUtil;
import com.gyp.jedis.api.channel.springredis.SpringRedis;
import com.gyp.jedis.model.dto.ResultDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Title: RedisPoolController
 * date 2019/11/29 13:38
 * Company: GYP
 *
 * @author GYP
 * @version 1.0
 */
@RestController
@RequestMapping("/redis")
@Slf4j
@Scope("prototype")
public class RedisPoolController {


    private int index = 0;
    private static int staticIndex = 0;

    @Autowired
    private SpringRedis springRedis;

    @GetMapping("/1/")
    public String get() {

        index++;
        staticIndex++;
        return index + " " + staticIndex + "";
    }

    @GetMapping("/put")
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(name = "str", value = "字符串", required = true, type = "String", paramType = "query"),
            }
    )
    public ResultDto put(String str) {

        springRedis.test();
        // RedisPoolsUtil.getSingleton().set("00000001", "123");
        // System.out.println(RedisPoolsUtil.getSingleton().get("00000001"));
        // RedisPoolsUtil.getSingleton().set("00000001", "321");
        // System.out.println(RedisPoolsUtil.getSingleton().get("00000001"));
        return ResultCreator.success();
    }


    public void test() {

        RedisPoolsUtil redisUtil = RedisPoolsUtil.getSingleton();

        redisUtil.set("aaa", "1234");

        Long aaa = redisUtil.del("aaa");

    }

    public void getString() {

        RedisPoolsUtil redisUtil = RedisPoolsUtil.getSingleton();

        List<String> aaa = redisUtil.mget("1", "aaa");

    }

    public static void main(String[] args) {
        setHash();
    }

    public static void setHash() {

    }

    public static void test(String... temp) {
        System.out.println(JSON.toJSONString(temp));
    }

}
