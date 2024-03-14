package com.javaclimb.shopping.controller.backend;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaclimb.shopping.common.base.BaseController;
import com.javaclimb.shopping.common.dto.JsonResult;
import com.javaclimb.shopping.common.util.PageUtil;
import com.javaclimb.shopping.entity.Mark;
import com.javaclimb.shopping.service.ProductService;
import com.javaclimb.shopping.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * 我的收藏查询和取消收藏 控制器
 */
@Controller("backMarkController")
public class MarkController extends BaseController {
    @Autowired
    private MarkService markService;

    @Autowired
    private ProductService ProductService;

    /*
        我的收藏列表
     */
    @RequestMapping("/admin/mark")
    public String markList(@RequestParam(value = "page",defaultValue = "1")Long pageNumber, @RequestParam(value = "size",defaultValue = "6")Long pageSize, Model model){
        Page page = PageUtil.initMpPage(pageNumber,pageSize);
        Mark condition = new Mark();
        condition.setUserId(getLoginUserId());
        Page <Mark> markPage = markService.findAll(page,condition);
        for(Mark mark:markPage.getRecords()){
            mark.setProduct(ProductService.get(mark.getProductId()));
        }
        model.addAttribute("pageInfo",markPage);
        model.addAttribute("tab","mark-list");
        model.addAttribute("pagePrefix","/admin/mark?");
        return "admin/mark-list";
    }

    /*
       取消收藏
    */
    @RequestMapping(value = "/admin/mark/cancel",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult cancelMark(@RequestParam("id")Long id){
       Mark mark = markService.get(id);
       if(mark == null||!Objects.equals(mark.getUserId(),getLoginUserId())){
           return JsonResult.error("取消收藏失败");
       }
        markService.delete(id);
       return JsonResult.success("取消收藏成功");
    }
}
