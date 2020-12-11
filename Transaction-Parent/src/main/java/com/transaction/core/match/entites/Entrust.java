package com.transaction.core.match.entites;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * entrust
 * 2019-02-26 18:09:07
 */
@Data
@Accessors(chain = true)
public class Entrust {
    /**
     * entrust.id
     * 2019-02-26 18:09:07
     */
    private Long id;

    /**
     * 用户ID
     * entrust.user_id
     * 2019-02-26 18:09:07
     */
    private Long userId;

    /**
     * 委托单号
     * entrust.entrust_no
     * 2019-02-26 18:09:07
     */
    private String entrustNo;

    /**
     * entrust.count
     * 2019-02-26 18:09:07
     */
    private Integer count;

    /**
     * 商品代码
     * entrust.commodity_code
     * 2019-02-26 18:09:07
     */
    private String commodityCode;

    /**
     * 买或卖
     * entrust.bs
     * 2019-02-26 18:09:07
     */
    private Boolean bs;

    /**
     * 单价
     * entrust.price
     * 2019-02-26 18:09:07
     */
    private BigDecimal price;

    /**
     * 订单状态
     * entrust.stage
     * 2019-02-26 18:09:07
     */
    private Integer stage;

}