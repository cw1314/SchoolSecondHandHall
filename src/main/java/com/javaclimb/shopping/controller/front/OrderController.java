package com.javaclimb.shopping.controller.front;

import com.javaclimb.shopping.common.base.BaseController;
import com.javaclimb.shopping.common.dto.JsonResult;
import com.javaclimb.shopping.common.enums.OrderStatusEnum;
import com.javaclimb.shopping.common.enums.ProductStatusEnum;
import com.javaclimb.shopping.entity.Order;
import com.javaclimb.shopping.entity.Product;
import com.javaclimb.shopping.entity.User;
import com.javaclimb.shopping.service.OrderService;
import com.javaclimb.shopping.service.ProductService;
import com.javaclimb.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Objects;

/**
 * 前端订单控制器
 */
@Controller("frontOrderController")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    /**
     * 创建订单
     */
    @RequestMapping("/order/create")
    @ResponseBody
//    public JsonResult createOrder(@RequestParam("productId")Long productId,@RequestParam(value = "num",defaultValue = "1")Integer num){
    public JsonResult createOrder(@RequestParam("productId")Long productId){
        System.out.println("1222");
        if(getLoginUser() == null){
            return JsonResult.error("用户未登录");
        }

        Product product = productService.get(productId);
        if(product ==null){
            return JsonResult.error("商品不存在");
        }

        User sellerUser = userService.get(product.getUserId());
        if(sellerUser == null){
            return JsonResult.error("卖家用户不存在");
        }
       Date startDate = new Date();

        //创建订单
        Order order = new Order();
        order.setCreateTime(new Date());
        order.setBuyerUserId(getLoginUserId());
        order.setSellerUserId(product.getUserId());
        order.setProductId(productId);
        order.setStatus(OrderStatusEnum.NOT_PAY.getValue());
        order.setNum(1);
        order.setTotalAmount(product.getPrice()*1);
        order.setStartDate(startDate);
        orderService.insert(order);
        return JsonResult.success("订单创建成功，请付款",order.getId());
    }
    /**
     * 查看合同信息
     */
    @RequestMapping("/order/agreement/view")
    public String orderView(@RequestParam(value = "orderId")Long orderId, Model model){
        if(getLoginUser() == null){
            return "redirect:/";
        }
        //订单不存在
        Order order = orderService.get(orderId);
        if(order == null){
            return renderNotFound();
        }

        //
        if(!Objects.equals(getLoginUserId(),order.getBuyerUserId())&&!Objects.equals(getLoginUserId(),order.getSellerUserId())&&!loginUserIsAdmin()){
            return renderNotAllowAccess();
        }
        //该订单的商品信息
        order.setProduct(productService.get(order.getProductId()));
        //该订单的买家信息
        order.setBuyerUser(userService.get(order.getBuyerUserId()));
        //该订单的卖家信息
        order.setSellerUser(userService.get(order.getSellerUserId()));
        model.addAttribute("order",order);
        return "front/agreement";
    }

    /**
     *
     */
    @RequestMapping("/order/agreement/submit")
    @ResponseBody
    public JsonResult agreementSubmit(@RequestParam(value = "orderId")Long orderId, Model model){
        if(getLoginUser() == null){
            return JsonResult.error("用户未登录");
        }
        //订单不存在
        Order order = orderService.get(orderId);
        if(order == null){
            return JsonResult.error("订单不存在");
        }

        //登录用户不是订单的买家，卖家或管理员，就不能查看
        if(!Objects.equals(getLoginUserId(),order.getBuyerUserId())&&!Objects.equals(getLoginUserId(),order.getSellerUserId())&&!loginUserIsAdmin()){
            return JsonResult.error("没有权限");
        }

        Product product = productService.get(order.getProductId());
        if(product ==null){
            return JsonResult.error("商品不存在");
        }


        if(Objects.equals(product.getStatus(), ProductStatusEnum.HAS_RENT.getValue())){
            return JsonResult.error("商品已租出或未释放");
        }

        Order checkOrder = orderService.getCurrentEffectiveOrder(orderId);
        if(checkOrder!=null){
            return JsonResult.error("商品已租出或未释放");
        }

        User sellerUser = userService.get(product.getUserId());
        if(sellerUser == null){
            return JsonResult.error("卖家用户不存在");
        }

        order.setStatus(OrderStatusEnum.NOT_PAY.getValue());
        orderService.update(order);
        return JsonResult.success("合同签订成功，请支付订单",orderId);
    }

    /**
     * 转向支付页面
     */
    @RequestMapping("/order/pay")
    public String pay(@RequestParam(value = "orderId")Long orderId, Model model){
        if(getLoginUser() == null){
            return "redirect:/";
        }
        //订单不存在
        Order order = orderService.get(orderId);
        if(order == null){
            return renderNotFound();
        }

        //登录用户不是订单的买家，卖家或管理员，就不能查看
        if(!Objects.equals(getLoginUserId(),order.getBuyerUserId())&&!Objects.equals(getLoginUserId(),order.getSellerUserId())&&!loginUserIsAdmin()){
            return renderNotAllowAccess();
        }

        Product product = productService.get(order.getProductId());
        if(product ==null){
            return renderNotFound();
        }
        //该订单的商品信息
        order.setProduct(productService.get(order.getProductId()));
        model.addAttribute("order",order);
        return "front/pay";
    }


    /**
     * 模拟支付
     */
    @RequestMapping("/order/pay/submit")
    @ResponseBody
    public JsonResult paySubmit(@RequestParam(value = "orderId")Long orderId){
        if(getLoginUser() == null){
            return JsonResult.error("用户未登录");
        }
        //订单不存在
        Order order = orderService.get(orderId);
        if(order == null){
            return JsonResult.error("订单不存在");
        }

        //登录用户不是订单的买家，卖家或管理员，就不能查看
        if(!Objects.equals(getLoginUserId(),order.getBuyerUserId())&&!Objects.equals(getLoginUserId(),order.getSellerUserId())&&!loginUserIsAdmin()){
            return JsonResult.error("没有权限");
        }

        Product product = productService.get(order.getProductId());
        if(product ==null){
            return JsonResult.error("商品不存在");
        }



        User sellerUser = userService.get(product.getUserId());
        if(sellerUser == null){
            return JsonResult.error("卖家用户不存在");
        }

        order.setStatus(OrderStatusEnum.UNDELIVERED.getValue());
        orderService.update(order);

        //更新商品的状态
        product.setStatus(ProductStatusEnum.HAS_RENT.getValue());

        productService.update(product);

        return JsonResult.success("支付成功，");
    }


}
