package com.javaclimb.shopping.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.javaclimb.shopping.common.base.BaseEntity;
import lombok.Data;

/**
 * 收藏
 */
@Data
@TableName("t_mark")
public class Mark extends BaseEntity {
    /**
     * 收藏者用户id
     */
    private Long userId;
    /**
     * 商品id
     */
    private Long productId;

    /**
     * 商品信息
     */
    @TableField(exist = false)
    private Product product;

}
