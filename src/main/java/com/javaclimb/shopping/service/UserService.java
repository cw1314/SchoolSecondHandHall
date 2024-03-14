package com.javaclimb.shopping.service;

import com.javaclimb.shopping.common.base.BaseService;
import com.javaclimb.shopping.entity.User;
/*
用户服务接口
 */
public interface UserService extends BaseService<User,Long> {
    //根据用户名查询用户
    public User findByUserName(String userName);
}
