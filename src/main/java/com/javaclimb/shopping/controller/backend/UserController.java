package com.javaclimb.shopping.controller.backend;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaclimb.shopping.common.base.BaseController;
import com.javaclimb.shopping.common.constant.Constant;
import com.javaclimb.shopping.common.dto.JsonResult;
import com.javaclimb.shopping.common.enums.UserRoleEnum;
import com.javaclimb.shopping.common.enums.UserStatusEnum;
import com.javaclimb.shopping.common.util.PageUtil;
import com.javaclimb.shopping.entity.User;
import com.javaclimb.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * 用户管理控制器
 */
@Controller
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    /**
     * 用户列表
     */
    @RequestMapping("/admin/user")
    public String userList(@RequestParam(value = "page",defaultValue = "1")Long pageNumber, @RequestParam(value = "size",defaultValue = "6")Long pageSize, Model model){
        Page page = PageUtil.initMpPage(pageNumber,pageSize);
        User condition = new User();
        Page<User> userPage = userService.findAll(page,condition);
        model.addAttribute("pageInfo",userPage);
        model.addAttribute("pagePrefix","/admin/user?");
        model.addAttribute("tab","user-list");
        return "admin/user-list";
    }

    /**
     * 禁用用户
     */
    @RequestMapping("/admin/user/disable")
    @ResponseBody
    public JsonResult disableUser(@RequestParam("id")Long id){
        try {
            User user = userService.get(id);
            if(user == null){
                return JsonResult.error("没有这个用户");
            }
            if(UserRoleEnum.ADMIN.getValue().equalsIgnoreCase(user.getRole())){
                return JsonResult.error("无法禁用管理员");
            }
            user.setStatus(UserStatusEnum.DISABLE.getValue());
            userService.update(user);
        }catch(Exception e){
            return JsonResult.error("禁用用户失败");
        }
        return JsonResult.success("禁用用户成功");
    }

    /**
     * 启用用户
     */
    @RequestMapping("/admin/user/enable")
    @ResponseBody
    public JsonResult enableUser(@RequestParam("id")Long id){
        try {
            User user = userService.get(id);
            if(user == null){
                return JsonResult.error("没有这个用户");
            }
            user.setStatus(UserStatusEnum.ENABLE.getValue());
            userService.update(user);
        }catch(Exception e){
            return JsonResult.error("启用用户失败");
        }
        return JsonResult.success("启用用户成功");
    }

    /**
     * 进入修改密码界面
     */
    @RequestMapping("/admin/password")
    public String password( Model model){

        model.addAttribute("tab","password");
        return "admin/password";
    }

    /**
     * 更新密码
     */
    @RequestMapping("/admin/password/submit")
    @ResponseBody
    public JsonResult passwordSubmit(@RequestParam("oldPassword")String oldPassword,
                                     @RequestParam("newPassword")String  newPassword,
                                     @RequestParam("confirmPassword")String confirmPassword, HttpSession session){
        if(!Objects.equals(newPassword,confirmPassword)){
            return JsonResult.error("两次旧密码不一致");
        }
        User user = userService.get(getLoginUserId());
        if(user == null||!Objects.equals(user.getUserPass(),oldPassword)){
            return JsonResult.error("旧密码错误");
        }
        user.setUserPass(newPassword);
        userService.update(user);
        session.setAttribute(Constant.SESSION_USER_KEY,userService.get(getLoginUserId()));
        return JsonResult.success("更新密码成功，请重新登录");
    }
}
