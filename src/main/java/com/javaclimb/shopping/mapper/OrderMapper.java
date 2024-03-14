package com.javaclimb.shopping.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaclimb.shopping.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单mapper
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
