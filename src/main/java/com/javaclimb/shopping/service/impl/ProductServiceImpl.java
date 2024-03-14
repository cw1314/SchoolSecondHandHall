package com.javaclimb.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaclimb.shopping.common.vo.ProductSearchVo;
import com.javaclimb.shopping.entity.Product;
import com.javaclimb.shopping.mapper.ProductMapper;
import com.javaclimb.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/*
服务接口实现
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    /**
     * mapper对象
     */
    public BaseMapper<Product> getRepository(){
        return productMapper;
    }

    @Override
    public QueryWrapper<Product> getQueryWrapper(Product product) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        if(product!=null&&product.getUserId()!=null){
            queryWrapper.eq("user_id",product.getUserId());
        }
        return queryWrapper;
    }

    /**
     * 获取带参数的查询器
     * @param condition
     * @return
     */

    @Override
    public QueryWrapper<Product> getQueryWrapper(Map<String, Object> condition) {
        return null;
    }



    /**
     * 根据出租类型获取最新的n条
     * @param productType 商品类型
     * @param limit 限制条数
     * @return
     */
    @Override
    public List<Product> findTopList(String productType, Integer limit) {
        System.out.println("2");
        return productMapper.findTopList(productType,limit);
    }
    /**
     * 根据出租类型获取最新的n条
     *
     * @param limit 限制条数
     * @return
     */
    @Override
    public List<Product> findTopList2(Integer limit) {
        System.out.println("3");
        return productMapper.findTopList2(limit);
    }



    /**
     * 获取分页数据
     * @param productSearchVo
     * @param page
     * @return
     */

    @Override
    public Page<Product> getProductPage(ProductSearchVo productSearchVo, Page<Product> page) {
        if(productSearchVo == null){
            productSearchVo = new ProductSearchVo();
        }else{
                productSearchVo.setCity(productSearchVo.getCity().trim());


                productSearchVo.setProductType(productSearchVo.getProductType().trim());


                productSearchVo.setTitle(productSearchVo.getTitle().trim());


        }
        List<Product> list = productMapper.searchProduct(productSearchVo,page);
        page.setRecords(list);
        return page;
    }
}














