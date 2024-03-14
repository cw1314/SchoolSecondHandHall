package com.javaclimb.shopping.common.enums;

/**
 * 订单状态枚举
 */
public enum OrderStatusEnum {
    /**
     *
     * 订单状态-2买家已经取消 -1待付款 0待送达 1待评价 2已完成'
     */
    /*
    1待评价
     */
    EVALUATE(1),

    /*
    2已完成
     */
    COMPLETE(2),




    /**
     * 0待送达
     */
    UNDELIVERED(0),

    /**
     * -1待付款
     */
    NOT_PAY(-1),

    /**
     * -2买家已经取消
     */
    BUYER_CANCEL(-2);

    ;
    private Integer value;

    OrderStatusEnum(Integer value){
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
