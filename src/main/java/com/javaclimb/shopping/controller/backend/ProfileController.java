package com.javaclimb.shopping.controller.backend;

import com.javaclimb.shopping.common.base.BaseController;
import com.javaclimb.shopping.common.constant.Constant;
import com.javaclimb.shopping.common.dto.JsonResult;
import com.javaclimb.shopping.entity.User;
import com.javaclimb.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 个人信息控制器
 */
@Controller
@RequestMapping("/admin/profile")
public class ProfileController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     *个人信息页面
     */
    @RequestMapping("")
    public String profile(Model model){
        User user = getLoginUser();
        model.addAttribute("user",user);
        model.addAttribute("tab","my-profile");
        return "admin/my-profile";
    }
    /**
     * 个人信息 保存提交
     *
     */
    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult submitProfile(User user, HttpSession session){
        user.setId(getLoginUserId());
        userService.update(user);
        session.setAttribute(Constant.SESSION_USER_KEY,userService.get(getLoginUserId()));
        return JsonResult.success("保存成功");
    }


}
