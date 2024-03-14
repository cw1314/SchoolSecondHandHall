package com.javaclimb.shopping.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.javaclimb.shopping.common.base.BaseEntity;
import lombok.Data;

/*
用户信息
 */
@Data
@TableName("t_user")
public class User extends BaseEntity {
    /*
    登录名
     */
    private String userName;
    /*
    姓名
     */
    private String userDisplayName;

    /*
    手机号
     */
    private String phone;
    /*/*
    电子邮箱
     */
    private String email;
    /*
    密码
     */
    private String userPass;
    /*
    身份证
     */
    private String idCard;

    /*
    头像
     */
    private String userAvatar;
    /*
    个人描述
     */
    private String userDesc;
    /*
    状态1正常0仅用
     */
    private Integer status;
    /*
    角色
     */
    private String role;
    /*
    性别
     */
    private String sex;
    /*
    业余爱好
     */
    private String hobby;
    /*
    职业
     */
    private String job;


}
