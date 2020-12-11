package com.gyp.jedis.api.controller;

import com.gyp.jedis.feign.api.TheFirstFeignIntefaceDe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: FeignClientController
 * Description: 接口的真正实现部分
 * date 2019/12/9 10:27
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author GYP
 * @version 1.0
 */
@RestController
@RequestMapping("/feign")
public class FeignClientController {

    @Autowired
    private TheFirstFeignIntefaceDe theFirstFeignInteface;

    @GetMapping("/doTest")
    public String doTest() {

        String s = theFirstFeignInteface.firstFeignClient();
        return "success";
    }
}
