package com.javaclimb.shopping.common.vo;

import lombok.Data;

/**
 * 商品搜索封装参数
 */
@Data
public class ProductSearchVo {
    /**
     * 页码
     */
    private Integer page = 1;
    /**
     * 页大小
     */
    private Integer size = 4;
    /**
     * 状态0代表未租出
     */
    private Integer status = 0;
    /**
     * 商品名关键字
     */
    private String title = "";
    /**
     * xuexiao
     */
    private String city = "";
    /**
     * 类型
     */
    private String productType = "";


    /**
     * 获取分页前缀参数
     */
    public String getPagePrefix(){
        StringBuffer sb = new StringBuffer();
        sb.append("?title=").append(this.title.trim());
        sb.append("&productType=").append(this.productType.trim());
        sb.append("&city=").append(this.city.trim());
        return sb.toString();
    }

}
