package com.javaclimb.shopping.common.util;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


public class PageUtil {
    /**
     * 按创建时间倒序排序
     */
    public static Page initMpPage(long pageNumber,long pageSize) {
        return initMpPage(pageNumber,pageSize,"createTime","desc");
    }

        /**
         * MyBatis分页封装
         * pageNumber页码，第几页
         * pageSize页大小
         * sort 排序字段
         * order 倒序，升序
         */
    public static Page initMpPage(long pageNumber,long pageSize,String sort,String order){
        if(StringUtils.isNotEmpty(sort)){
            sort =camelToUnderline(sort);
        }
        if (pageNumber<1){
            pageNumber=1;
        }
        if(pageSize<1){
            pageSize=4;
        }
        if(pageSize>100){
            pageSize=100;
        }
        //倒序还是升序
        Boolean isAsc = false;
        if (StringUtils.isEmpty(sort)||"asc".equals(order.toLowerCase())){
            isAsc = true;
        }
        Page p = new Page(pageNumber,pageSize);
        if (isAsc){
            p.setAsc(sort);
        }else{
            p.setDesc(sort);
        }
        return p;
    }

    /**
     * 驼峰转下划线
     */
    public static String  camelToUnderline(String str){
        if(str==null||str.trim().isEmpty()){
            return "";
        }
        int len = str.length();
        StringBuilder sb = new StringBuilder(len);
        sb.append(str.substring(0,1).toLowerCase());
        for(int i=1;i<len;i++){
            char c = str.charAt(i);
            if(Character.isUpperCase(c)){
                sb.append("_");
                sb.append(Character.toLowerCase(c));
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
