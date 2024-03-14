package com.javaclimb.shopping.controller.front;

import com.javaclimb.shopping.common.base.BaseController;
import com.javaclimb.shopping.common.constant.Constant;
import com.javaclimb.shopping.common.dto.JsonResult;
import com.javaclimb.shopping.common.enums.UserStatusEnum;
import com.javaclimb.shopping.entity.User;
import com.javaclimb.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/*
注册控制器
 */
@Controller
@RequestMapping("/register")
public class RegisterController extends BaseController {
    @Autowired
    private UserService userService;
    /*
    注册提交
     */
    @RequestMapping("/submit")
    @ResponseBody
    public JsonResult registerSubmit(User user, HttpSession session){
        if(user == null){
            return JsonResult.error("非法访问");
        }
        User user1 = userService.findByUserName(user.getUserName());
        if (user1!=null){
            return JsonResult.error("用户已经存在");
        }
        user.setUserAvatar("/assets/img/default-avatar.jpg");
        user.setStatus(UserStatusEnum.ENABLE.getValue());
        user.setCreateTime(new Date());
        try{
            userService.insert(user);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("注册失败");
        }
        session.setAttribute(Constant.SESSION_USER_KEY,user);
        return JsonResult.success("注册成功");
    }
}








