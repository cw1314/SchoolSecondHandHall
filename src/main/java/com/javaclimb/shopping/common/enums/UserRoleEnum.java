package com.javaclimb.shopping.common.enums;

/**
 * 用户角色枚举
 */
public enum UserRoleEnum {
    /*
    管理员
     */
    ADMIN("admin"),

    /*

     */
    SELLER("seller"),

    /*

    */
    BUYER("buyer"),

    ;
    private String value;

    UserRoleEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
