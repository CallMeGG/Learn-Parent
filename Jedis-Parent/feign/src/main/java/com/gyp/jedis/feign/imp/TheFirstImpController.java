package com.gyp.jedis.feign.imp;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: TheFirstImpController
 * date 2019/12/10 11:15
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author GYP
 * @version 1.0
 */
@RestController
public class TheFirstImpController {

    @PostMapping("/feign/firstFeignClient")
    public String firstFeignClient() {

        return "first_step";
    }
}
