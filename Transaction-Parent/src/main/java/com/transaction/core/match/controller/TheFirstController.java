package com.transaction.core.match.controller;

import com.transaction.core.match.core.CoreTransaction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Title: TheFirstController
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author GYP
 * @version 1.0
 * @date 2019/2/15 17:45
 */
@RestController
@RequestMapping(value = {"/first"})
public class TheFirstController {

    @RequestMapping("/buy")
    public String buyCommdity() {

        CoreTransaction.getInstance().sendEnturst("001", true, 100, new BigDecimal("1000"), 2000001L);

        return "buy_success";
    }

    @RequestMapping("/sell")
    public String sellCommdity() {

        CoreTransaction.getInstance().sendEnturst("001", false, 100, new BigDecimal("1000"), 1000001L);
        return "sell_success";
    }
}
