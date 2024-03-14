package com.javaclimb.shopping.common.enums;

/**
 * 房子状态枚举
 */
public enum FeedBackStatusEnum {
    /**
     * 反馈状态 0未处理  1已处理
     */
    /*
    0未处理
     */
    NOT_HANDLE(0),
    /*
    1已处理
     */
    HAD_HANDLE(1)
    ;
    private Integer value;

    FeedBackStatusEnum(Integer value){
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
