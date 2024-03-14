package com.javaclimb.shopping.common.enums;

/**
 * 商品类型枚举
 */
public enum ProductTypeEnum {
    /*
    宿舍用品
     */
    DORM("dorm"),

    /*
   学习用品
     */
    STUDY("study"),

    /*
   闲置杂物
     */
    IDLE("idle"),

    ;
    private String value;

    ProductTypeEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
