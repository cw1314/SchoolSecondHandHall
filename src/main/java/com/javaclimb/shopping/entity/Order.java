package com.javaclimb.shopping.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.javaclimb.shopping.common.base.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_order")
public class Order extends BaseEntity {
    /**
     * 买家id
     */
    private Long buyerUserId;
    /**
     * 卖家用户id
     */
    private Long sellerUserId;
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 订单状态-3买家已经取消 -2待付款 -1待送达 0生效中 1待评价 2已完成'
     */
    private Integer status;
    /**
     * 单价
     */
    private Integer price;
    /**
     * 数量
     */
    private Integer num;
    /**
     * 总金额
     */
        private Integer totalAmount;
    /**
     * 开始日期
     */
    private Date startDate;


    /**
     * 商品信息
     */
    @TableField(exist = false)
    private Product product;

    /**
     * 买家用户信息
     */
    @TableField(exist = false)
    private User buyerUser;

    /**
     * 卖家用户信息
     */
    @TableField(exist = false)
    private User sellerUser;

}
