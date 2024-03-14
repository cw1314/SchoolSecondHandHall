package com.javaclimb.shopping.controller.front;

import com.javaclimb.shopping.common.base.BaseController;
import com.javaclimb.shopping.common.dto.JsonResult;
import com.javaclimb.shopping.common.enums.FeedBackStatusEnum;
import com.javaclimb.shopping.entity.FeedBack;
import com.javaclimb.shopping.entity.User;
import com.javaclimb.shopping.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 用户反馈前端控制器
 */
@Controller("frontFeedBackController")
public class FeedBackController  extends BaseController {
    @Autowired
    private FeedBackService feedBackService;

    @RequestMapping("/feedback")
    public String feedback(){
        return "front/feedback";
    }

    /**
     * 反馈信息提交
     */
    @RequestMapping(value = "/feedback/submit",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult submit(@RequestParam("contactName")String contactName,
                             @RequestParam("contactEmail")String contactEmail,
                             @RequestParam("title")String title,
                             @RequestParam("content")String content){
        //先判断用户是否已经登录
        User loginUser = getLoginUser();
        if(loginUser == null){
            return JsonResult.error("请先登录");
        }
        FeedBack feedBack = new FeedBack();
        feedBack.setContactEmail(contactEmail);
        feedBack.setContactName(contactName);
        feedBack.setContent(content);
        feedBack.setStatus(FeedBackStatusEnum.NOT_HANDLE.getValue());
        feedBack.setTitle(title);
        feedBack.setUserId(getLoginUserId());
        feedBack.setCreateTime(new Date());
        feedBackService.insert(feedBack);
        return  JsonResult.success("反馈成功，请耐心等待管理员处理");
    }
}
