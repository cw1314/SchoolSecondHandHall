package com.javaclimb.shopping.controller.front;

import com.javaclimb.shopping.common.base.BaseController;
import com.javaclimb.shopping.common.constant.Constant;
import com.javaclimb.shopping.service.OrderService;
import com.javaclimb.shopping.service.ProductService;
import com.javaclimb.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    //前端首页
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("productList",productService.findTopList2(Constant.INDEX_HOUSE_NUM));
       System.out.println("1");
        return "front/index1";
    }
}
