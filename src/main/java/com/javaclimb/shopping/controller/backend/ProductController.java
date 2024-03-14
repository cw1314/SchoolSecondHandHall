package com.javaclimb.shopping.controller.backend;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaclimb.shopping.common.base.BaseController;
import com.javaclimb.shopping.common.constant.Constant;
import com.javaclimb.shopping.common.dto.JsonResult;
import com.javaclimb.shopping.common.enums.ProductStatusEnum;
import com.javaclimb.shopping.common.util.PageUtil;
import com.javaclimb.shopping.entity.Product;
import com.javaclimb.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 商品控制器
 */
@Controller("backendproductController")
@RequestMapping("/admin/product")
public class ProductController extends BaseController {
    @Autowired
    private ProductService productService;
    /**
     * 进入商品管理页面
     */
    @RequestMapping("")
    public String productList(@RequestParam(value = "page",defaultValue = "1")Long pageNumber, @RequestParam(value = "size",defaultValue = "6")Long pageSize, Model model){
        Page page = PageUtil.initMpPage(pageNumber,pageSize);
        Product condition = new Product();
        //如果登录用户是管理员可以查询所有商品，如果登录用户不是管理员，只能查询自己上传的商品
        if(!loginUserIsAdmin()){
            condition.setUserId(getLoginUserId());
        }
        Page<Product> productPage = productService.findAll(page,condition);
        model.addAttribute("pageInfo",productPage);
        model.addAttribute("pagePrefix","/admin/product?");
        model.addAttribute("isAdmin",loginUserIsAdmin());
        model.addAttribute("tab","product-list");
        return "admin/product-list1";
    }
    /**
     * 进入商品发布页面
     */
    @RequestMapping("/publish")
    public String publish(@RequestParam(value = "id",required = false)Long id,Model model){
        System.out.println("publish........................");
        Product product = new Product();
        //编辑页面
        if(id!=null){
            product = productService.get(id);
            if(product==null){
                return renderNotFound();
            }
            //如果是编辑别人的商品并且没有管理员权限，跳转403
            System.out.println(getLoginUserId());
            if(!loginUserIsAdmin() && !Objects.equals(product.getUserId(),getLoginUserId())){
                return renderNotAllowAccess();
            }
        }
        model.addAttribute("product",product);
        return "admin/product-publish";
    }
    /**
     * 发布商品提交
     */
    @RequestMapping("/publish/submit")
    @ResponseBody
    public JsonResult publishSubmit(Product product, @RequestParam("key")String key, HttpSession session){
        System.out.println("submit....");

        try {
           if (product.getId() == null){    //新增
               product.setCreateTime(new Date());
               product.setUserId(getLoginUserId());
           }else{   //修改
               Product queryproduct = productService.get(product.getId());
               if(queryproduct == null){
                   return JsonResult.error("发布失败，没有这个商品");
               }
               //如果是编辑别人的商品并且没有管理员权限，跳转403
               if(!loginUserIsAdmin() && Objects.equals(product.getUserId(),getLoginUserId())){
                   return JsonResult.error("发布失败，你不能编辑别人的商品");
               }
           }
           product.setStatus(ProductStatusEnum.NOT_CHECK.getValue());
           //获取轮播图
           String sessionKey = Constant.SESSION_IMG_PREFIX + key;
           List<String> imgList = (List<String>)session.getAttribute(sessionKey);
           if(imgList!=null&&imgList.size()>0){
               //把轮播图转换json格式存储
               product.setSlideUrl(JSON.toJSONString(imgList));
               //把轮播图的第一个图放到缩略图
               product.setThumbnailUrl(imgList.get(0));
           }
           productService.insertOrUpdate(product);
       }catch(Exception e){
           e.printStackTrace();
           return JsonResult.error("发布失败,请填写完整信息");
       }
        System.out.println(product);
       return JsonResult.success("发布成功",product.getId());
    }

    /**
     * 下架商品
     */
    @RequestMapping("/down")
    @ResponseBody
    public JsonResult downProduct(@RequestParam("id")Long id){
        try {
            Product product = productService.get(id);
            if(product == null){
                return JsonResult.error("没有这个商品");
            }
            //如果是下架别人的商品并且没有管理员权限，跳转403
            if(!loginUserIsAdmin() && !Objects.equals(product.getUserId(),getLoginUserId())){
                return JsonResult.error("你不能下架别人的商品");
            }
            if(Objects.equals(product.getStatus(), ProductStatusEnum.HAS_RENT.getValue())){
                return JsonResult.error("商品正在租住中，不能下架");
            }
            product.setStatus(ProductStatusEnum.HAS_DOWN.getValue());
            productService.update(product);
        }catch(Exception e){
            return JsonResult.error("下架商品失败");
        }
        return JsonResult.success("下架成功");
    }
    /**
     * 上架商品
     */
    @RequestMapping("/up")
    @ResponseBody
    public JsonResult upProduct(@RequestParam("id")Long id){
        try {
            Product product = productService.get(id);
            if(product == null){
                return JsonResult.error("没有这个商品");
            }
            //如果是上架别人的商品并且没有管理员权限，跳转403
            if(!loginUserIsAdmin() && !Objects.equals(product.getUserId(),getLoginUserId())){
                return JsonResult.error("你不能上架别人的商品");
            }
            if(Objects.equals(product.getStatus(), ProductStatusEnum.HAS_RENT.getValue())){
//                return JsonResult.error("商品正在租住中，不能上架");
                return JsonResult.error("，不能上架");
            }
            product.setStatus(ProductStatusEnum.NOT_RENT.getValue());
            productService.update(product);
        }catch(Exception e){
            return JsonResult.error("上架商品失败");
        }
        return JsonResult.success("上架成功");
    }

    /**
     * 审核商品
     */
    @RequestMapping("/checkPass")
    @ResponseBody
    public JsonResult checkPassproduct(@RequestParam("id")Long id){
        try {
            Product product = productService.get(id);
            if(product == null){
                return JsonResult.error("没有这个商品");
            }
            //只有管理员有权限审核
            if(!loginUserIsAdmin() && Objects.equals(product.getUserId(),getLoginUserId())){
                return JsonResult.error("没有权限审核");
            }
            if(!Objects.equals(product.getStatus(), ProductStatusEnum.NOT_CHECK.getValue())){
                return JsonResult.error("只能审核待审核的商品");
            }
            product.setStatus(ProductStatusEnum.NOT_RENT.getValue());
            productService.update(product);
        }catch(Exception e){
            return JsonResult.error("审核失败");
        }
        return JsonResult.success("审核成功");
    }

    /**
     * 审核驳回
     */
    @RequestMapping("/checkReject")
    @ResponseBody
    public JsonResult checkRejectproduct(@RequestParam("id")Long id){
        try {
            Product product = productService.get(id);
            if(product == null){
                return JsonResult.error("没有这个商品");
            }
            //只有管理员有权限审核
            if(!loginUserIsAdmin() && Objects.equals(product.getUserId(),getLoginUserId())){
                return JsonResult.error("没有权限审核");
            }
            if(!Objects.equals(product.getStatus(), ProductStatusEnum.NOT_CHECK.getValue())){
                return JsonResult.error("只能审核待审核的商品");
            }
            product.setStatus(ProductStatusEnum.CHECK_REJECT.getValue());
            productService.update(product);
        }catch(Exception e){
            return JsonResult.error("审核失败");
        }
        return JsonResult.success("审核成功");
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult deleteproduct(@RequestParam("id")Long id){
        try {
            Product product = productService.get(id);
            if(product == null){
                return JsonResult.error("没有这个商品");
            }
            //如果是删除别人的商品并且没有管理员权限，提示没权限
            if(!loginUserIsAdmin() && !Objects.equals(product.getUserId(),getLoginUserId())){
                return JsonResult.error("没有权限删除，这不是你的商品");
            }
            if(Objects.equals(product.getStatus(), ProductStatusEnum.HAS_RENT.getValue())){
                return JsonResult.error("商品正在租住中，不能删除");
            }
            productService.delete(id);
        }catch(Exception e){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功");
    }
}
