package com.javaclimb.shopping.controller.backend;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaclimb.shopping.common.base.BaseController;
import com.javaclimb.shopping.common.dto.JsonResult;
import com.javaclimb.shopping.common.enums.OrderStatusEnum;
import com.javaclimb.shopping.common.util.PageUtil;
import com.javaclimb.shopping.entity.Order;
import com.javaclimb.shopping.service.OrderService;
import com.javaclimb.shopping.service.ProductService;
import com.javaclimb.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * 后端订单控制器
 */
@Controller("backendOrderController")
@RequestMapping("/admin/order")
public class OrderController  extends BaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService ProductService;

    @Autowired
    private UserService userService;
    /**
     * 订单列表
     */
    @RequestMapping("")
    public String allOrder(@RequestParam(value = "page",defaultValue = "1")Long pageNumber, @RequestParam(value = "size",defaultValue = "6")Long pageSize, Model model){
        Page page = PageUtil.initMpPage(pageNumber,pageSize);
        Order condition = new Order();
        //如果说登录用户是用户就查询用户的订单
        if(loginUserIsBuyer()){
            condition.setBuyerUserId(getLoginUserId());
        }
        //如果是房东查询该房东的订单
        if(loginUserIsSeller()){
            condition.setSellerUserId(getLoginUserId());
        }
        //如果是管理员就查询所有订单
        Page<Order> orderPage = orderService.findAll(page,condition);
        for(Order order:orderPage.getRecords()){
            order.setProduct(ProductService.get(order.getProductId()));
            order.setSellerUser(userService.get(order.getSellerUserId()));
            order.setBuyerUser(userService.get(order.getBuyerUserId()));
        }
        model.addAttribute("pageInfo",orderPage);
        model.addAttribute("tab","order-list");
        model.addAttribute("pagePrefix","/admin/order?");
        return "admin/order-list";
    }



    /**
     * 取消订单
     */
    @RequestMapping(value = "/cancel",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult cancelOrder(@RequestParam("orderId")Long orderId){
        //校验订单是否存在
        Order order = orderService.get(orderId);
        if(order == null){
            return JsonResult.error("订单不存在");
        }
        //登录用户不是管理员，也不是该订单的房东，就不能看取消订单
        if(!loginUserIsAdmin()&&!Objects.equals(getLoginUserId(),order.getBuyerUserId())&&!Objects.equals(getLoginUserId(),order.getSellerUserId())){
            return JsonResult.error("没有权限");
        }
        order.setStatus(OrderStatusEnum.BUYER_CANCEL.getValue());
        orderService.update(order);
        return JsonResult.success("订单取消成功");
    }

    /**
     * 确认收货
     */
    @RequestMapping(value = "/endPass",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult endOrderPass(@RequestParam("orderId")Long orderId){
        //校验订单是否存在
        Order order = orderService.get(orderId);
        if(order == null){
            return JsonResult.error("订单不存在");
        }

        order.setStatus(OrderStatusEnum.COMPLETE.getValue());

        orderService.update(order);


        return JsonResult.success("确认收货成功");
    }




}
