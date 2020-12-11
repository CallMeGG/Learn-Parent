package com.gyp.jedis.api.controller;

import com.gyp.jedis.ResultCreator;
import com.gyp.jedis.model.dto.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

/**
 * Title: WebRequestTestController
 * date 2020/3/25 13:13
 *
 * @author GYP
 * @version 1.0
 */
@RestController
@RequestMapping("/web")
@Slf4j
public class WebRequestTestController {

    @PostMapping("/test")
    public ResultDto test() throws InterruptedException {
        Thread.sleep(15000);
        return ResultCreator.success();
    }

    @PostMapping("/test1")
    public ResultDto test1() throws InterruptedException {
        return ResultCreator.success();
    }

    @GetMapping("/hello")
    public Callable<String> helloController() {
        log.info(Thread.currentThread().getName() + " 进入helloController方法");
        Callable<String> callable = () -> {
            log.info(Thread.currentThread().getName() + " 进入call方法");
            log.info(Thread.currentThread().getName() + " 从helloService方法返回");
            return "OK";
        };
        log.info(Thread.currentThread().getName() + " 从helloController方法返回");
        return callable;

    }

}
