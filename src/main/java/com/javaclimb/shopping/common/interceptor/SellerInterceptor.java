package com.javaclimb.shopping.common.interceptor;


import com.javaclimb.shopping.common.constant.Constant;
import com.javaclimb.shopping.common.enums.UserRoleEnum;
import com.javaclimb.shopping.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 房东接口拦截器
 */
@Component
public class SellerInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception{
        User user = (User) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
        //如果用户未登录，拦截
        if(user == null){
            response.sendRedirect("/");
            return  false;
        }
        //如果用户不是租客，拦截
        if(UserRoleEnum.SELLER.getValue().equalsIgnoreCase(user.getRole())){
            return false;
        }
        return true;
    }

}
