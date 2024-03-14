package com.javaclimb.shopping.controller.backend;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaclimb.shopping.common.base.BaseController;
import com.javaclimb.shopping.common.dto.JsonResult;
import com.javaclimb.shopping.common.util.PageUtil;
import com.javaclimb.shopping.entity.FeedBack;
import com.javaclimb.shopping.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 *
 * 后台管理控制器
 */
@Controller("backFeedbackController")
public class FeedBackController extends BaseController {
    @Autowired
    private FeedBackService feedBackService;

    /**
     * 进入反馈页面
     */
    @RequestMapping("/admin/feedback")
    public String feedback(@RequestParam(value = "page",defaultValue = "1")Long pageNumber, @RequestParam(value = "size",defaultValue = "6")Long pageSize, Model model){
        Page page = PageUtil.initMpPage(pageNumber,pageSize);
        FeedBack condition = new FeedBack();
        //如果不是管理员，只查询自己的反馈
        if(!loginUserIsAdmin()){
            condition.setUserId(getLoginUserId());
        }
        Page<FeedBack> feedBackPage = feedBackService.findAll(page,condition);
        model.addAttribute("pageInfo",feedBackPage);
        model.addAttribute("pagePrefix","/feedback?");
        model.addAttribute("tab","feedback-list");
        model.addAttribute("isAdmin",loginUserIsAdmin());
        return "admin/feedback-list";
    }
    /**
     * 回复反馈
     */

    @RequestMapping(value = "/admin/feedback/reply/submit",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult replySubmit(FeedBack feedBack){
        feedBackService.update(feedBack);
        return JsonResult.success("回复成功");
    }

    /**
     * 删除反馈
     */
    @RequestMapping("/admin/feedback/delete")
    @ResponseBody
    public JsonResult deleteFeedback(@RequestParam("id")Long id){
        try {
            FeedBack feedBack = feedBackService.get(id);
            if(feedBack == null){
                return JsonResult.error("反馈不存在");
            }
           if(!loginUserIsAdmin()&&!Objects.equals(feedBack.getUserId(),getLoginUserId())){
               return JsonResult.error("没有权限删除,这不是你的反馈");
           }
           feedBackService.delete(id);
        }catch(Exception e){
            return JsonResult.error("删除反馈失败");
        }
        return JsonResult.success("删除反馈成功");
    }
}


