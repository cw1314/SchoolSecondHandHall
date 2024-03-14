package com.javaclimb.shopping.service;

import com.javaclimb.shopping.common.base.BaseService;
import com.javaclimb.shopping.entity.Order;

/**
 * 订单服务接口
 */
public interface OrderService extends BaseService<Order,Long> {
    /**
     * 查询当前有效订单
     */
    public Order getCurrentEffectiveOrder(Long houseId);


}
