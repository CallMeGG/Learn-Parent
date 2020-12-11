package com.transaction.core.match.entites;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * order
 * 2019-02-26 17:48:59
 */
@Data
@Accessors(chain = true)
public class Orders {
    /**
     * order.id
     * 2019-02-26 17:49:00
     */
    private Long id;

    /**
     * 订单号
     * order.order_no
     * 2019-02-26 17:49:00
     */
    private String orderNo;

    /**
     * 委托单号
     * order.entrust_no
     * 2019-02-26 17:49:00
     */
    private String entrustNo;

    /**
     * 订单时间
     * order.add_time
     * 2019-02-26 17:49:00
     */
    private Date addTime;

    /**
     * 成交数量
     * order.count
     * 2019-02-26 17:49:00
     */
    private Integer count;

    /**
     * 成交单价
     * order.price
     * 2019-02-26 17:49:00
     */
    private BigDecimal price;

    /**
     * 成交类型
     * order.type
     * 2019-02-26 17:49:00
     */
    private Integer type;

    /**
     * 订单总价
     * order.tatol_price
     * 2019-02-26 17:49:00
     */
    private BigDecimal tatolPrice;

    /**
     * 买卖
     * order.bs
     * 2019-02-26 17:49:00
     */
    private Boolean bs;

    /**
     * order.user_id
     * 2019-02-26 17:49:00
     */
    private Long userId;

}