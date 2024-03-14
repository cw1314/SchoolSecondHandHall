package com.javaclimb.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaclimb.shopping.common.enums.OrderStatusEnum;
import com.javaclimb.shopping.entity.Order;
import com.javaclimb.shopping.mapper.OrderMapper;
import com.javaclimb.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 订单服务实现类
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    //mapper对象
    @Override
    public BaseMapper<Order> getRepository() {
        return orderMapper;
    }

    /**
     * 获得查询器
     * @param order
     * @return
     */
    @Override
    public QueryWrapper<Order> getQueryWrapper(Order order) {
        return null;
    }

    @Override
    public QueryWrapper<Order> getQueryWrapper(Map<String, Object> condition) {
        return null;
    }

    @Override
    public Order getCurrentEffectiveOrder(Long productId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("product_id",productId);
        queryWrapper.eq("status", OrderStatusEnum.UNDELIVERED);
        return orderMapper.selectOne(queryWrapper);
    }

}
