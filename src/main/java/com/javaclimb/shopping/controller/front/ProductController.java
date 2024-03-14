package com.javaclimb.shopping.controller.front;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaclimb.shopping.common.base.BaseController;
import com.javaclimb.shopping.common.util.PageUtil;
import com.javaclimb.shopping.common.vo.ProductSearchVo;
import com.javaclimb.shopping.entity.Product;
import com.javaclimb.shopping.service.OrderService;
import com.javaclimb.shopping.service.ProductService;
import com.javaclimb.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 *
 * 前台商品控制信息
 */
@Controller("frontProductController")
public class ProductController extends BaseController {
    @Autowired
    public ProductService productService;

    @Autowired
    public OrderService orderService;

    @Autowired
    public UserService userService;

    /**
     * 商品详情
     */
    @RequestMapping("/product/detail/{id}")
    public String productDetail(@PathVariable("id")Long id, Model model){
        //根据id查询商品
        Product product = productService.get(id);
        if(product == null){
            return renderNotFound();
        }
        //处理轮播图url
        List<String> slideList = JSON.parseArray(product.getSlideUrl(),String.class);
        product.setSlideImgList(slideList);
        model.addAttribute("product",product);
        return "front/product-detail1";
    }

    /**
     * 商品列表
     */
    @RequestMapping("/product")
    public String productList(ProductSearchVo productSearchVo, Model model){
        Page page = PageUtil.initMpPage(productSearchVo.getPage(),productSearchVo.getSize());
        Page<Product> productPage = productService.getProductPage(productSearchVo,page);
        model.addAttribute("pageInfo",productPage);
        model.addAttribute("productSearchVo",productSearchVo);
        model.addAttribute("pagePrefix",productSearchVo.getPagePrefix());
        return "front/product-list";
    }
}
