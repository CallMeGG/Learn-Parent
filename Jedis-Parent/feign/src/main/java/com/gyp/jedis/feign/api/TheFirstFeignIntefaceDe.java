package com.gyp.jedis.feign.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Title: TheFirstFeignInteface
 * date 2019/12/6 17:14
 * Company: GYP
 *
 * @author GYP
 * @version 1.0
 */
@Primary
@FeignClient(url = "${gyp.server.feign.url}", value = "feignService")
public interface TheFirstFeignIntefaceDe {

    @PostMapping("/feign/firstFeignClient")
    String firstFeignClient();
}
