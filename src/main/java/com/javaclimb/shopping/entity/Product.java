package com.javaclimb.shopping.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.javaclimb.shopping.common.base.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * 房子信息
 */
@Data
@TableName("t_product")
public class Product extends BaseEntity {

    /**
     * 卖家用户id
     */
    private Long userId;
    /**
     * 商品类型
     */
    private String productType;
    /**
     * 商品名称
     *
     */
    private String title;
    /**
     * 详细描述
     *
     */
    private String content;
    /**
     *
     *
     */
    private String city;
    /**
     * 详细地址
     *
     */
    private String address;
    /**
     * 缩略图
     *
     */
    private String thumbnailUrl;
    /**
     * 轮播图url
     *
     */
    private String slideUrl;
    /**
     * 单价
     *
     */
    private Integer price;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 状态
     *
     */
    private Integer status;

    /**
     * 联系人姓名
     *
     */
    private String contactName;
    /**
     * 联系人电话
     *
     */
    private String contactPhone;
    /**
     * 轮播图列表
     */
    @TableField(exist = false)//声明不是数据库的字段
    private List<String> slideImgList;


    /**
     * 加上订dan
     */
    @TableField(exist = false)//声明不是数据库的字段
    private Order currentOrder;





}
