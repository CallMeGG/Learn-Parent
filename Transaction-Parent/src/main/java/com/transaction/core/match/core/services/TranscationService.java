package com.transaction.core.match.core.services;

import com.transaction.core.match.entites.Entrust;
import com.transaction.core.match.entites.Orders;
import com.transaction.core.match.mapper.EntrustMapper;
import com.transaction.core.match.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Title: TranscationService
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author GYP
 * @version 1.0
 * @date 2019/2/26 15:12
 */
@Service
public class TranscationService {

    @Autowired
    private EntrustMapper entrustMapper;

    @Autowired
    private OrdersMapper orderMapper;

    public void addOrder(Orders order) {
        try {
            this.orderMapper.insertSelective(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addEntrust(Entrust entrust) {
        this.entrustMapper.insertSelective(entrust);
    }

}
