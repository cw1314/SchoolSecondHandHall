package com.javaclimb.shopping.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaclimb.shopping.common.base.BaseService;
import com.javaclimb.shopping.common.vo.ProductSearchVo;
import com.javaclimb.shopping.entity.Product;

import java.util.List;

/*
服务接口
 */
public interface ProductService extends BaseService<Product,Long> {


    /**
     * 根据出租类型，获取最新的n条房子
     *
     */
    public List<Product> findTopList(String productType, Integer limit);
    /**
     * 获取最新的n条房子
     *
     */
    public List<Product> findTopList2(Integer limit);
    /**
     * 获取分页数据
     */
    Page<Product> getProductPage(ProductSearchVo productSearchVo, Page<Product> page);
}
