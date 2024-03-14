package com.javaclimb.shopping.common.enums;

/**
 * 商品状态枚举
 */
public enum ProductStatusEnum {
    /**
     * 状态：0未售出 1已售出 -1已下架 -2待审核 -3审核不通过
     */
    /*
    0未售出
     */
    NOT_RENT(0),
    /*
    1已售出
     */
    HAS_RENT(1),
    /**
     * -1已下架
     */
    HAS_DOWN(-1),
    /**
     * -2待审核
     */
    NOT_CHECK(-2),
    /**
     * 审核不通过
     */
    CHECK_REJECT(-3);

    ;
    private Integer value;

    ProductStatusEnum(Integer value){
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
