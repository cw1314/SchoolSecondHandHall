package com.javaclimb.shopping.common.base;


import com.javaclimb.shopping.common.constant.Constant;
import com.javaclimb.shopping.common.enums.UserRoleEnum;
import com.javaclimb.shopping.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

//所有controller控制器的基类
@Controller
public class BaseController {

    @Autowired
    protected HttpServletRequest request;

    /**
     * 获取当前登录用户
     */
    public User getLoginUser(){
        User user = (User)request.getSession().getAttribute(Constant.SESSION_USER_KEY);
        return user;
    }
    /**
     * 获取当前用户的ID
     */
    public Long getLoginUserId(){
        User user = getLoginUser();
        if(user == null){
            return null;
        }
        return user.getId();
    }
    /**
     * 当前用户是管理员
     */
    public Boolean loginUserIsAdmin(){
        User user = getLoginUser();
        if(user==null){
            return false;
        }
        return UserRoleEnum.ADMIN.getValue().equalsIgnoreCase(user.getRole());
    }
    /**
     * 当前用户是管理员
     */
    public Boolean loginUserIsSeller(){
        User user = getLoginUser();
        if(user==null){
            return false;
        }
        return UserRoleEnum.SELLER.getValue().equalsIgnoreCase(user.getRole());
    }
    /**
     * 当前用户是用户
     */
    public Boolean loginUserIsBuyer(){
        User user = getLoginUser();
        if(user==null){
            return false;
        }
        return UserRoleEnum.BUYER.getValue().equalsIgnoreCase(user.getRole());
    }
    /**
     * 渲染404页面
     */
    public String renderNotFound(){
        return "forward:/404";
    }
    /**
     * 无权限访问
     */
    public String renderNotAllowAccess(){
        return "forward:/403";
    }
    /**
     * 服务器异常
     */
    public String renderServerException(){
        return "forward:/500";
    }
    /**
     * 系统异常
     */
    public String renderError(){
        return "forward:/error";
    }
}
