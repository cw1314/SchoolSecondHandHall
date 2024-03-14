package com.javaclimb.shopping.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaclimb.shopping.common.vo.ProductSearchVo;
import com.javaclimb.shopping.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*
房子数据访问层
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    /**
     * 根据商品类型，获取最新的n条商品信息
     * 像这种比较简单的sql,可以直接用注解的方式在方法中写
     */
    @Select({"select * from t_product where status = 0 and product_type = #{productType} order by create_time desc limit #{limit}"})
    public List<Product> findTopList(@Param("productType")String productType, @Param("limit")Integer limit);
    /**
     * 获取最新的n条房子信息
     *
     */
    @Select("select * from t_product where status = 0 order by create_time desc limit #{limit}")
    public List<Product> findTopList2( @Param("limit")Integer limit);

    /**
     * 搜索房子
     * 一个参数的时候可以不写@Param：两个或者两个以上的时候，一定要写
     * 像这种比较复杂的sql,我们最好写在xml里面 ProductMapper.xml
     */
    public List<Product> searchProduct(@Param("productSearchVo") ProductSearchVo prooductSearchVo, @Param("page") Page page);

}
