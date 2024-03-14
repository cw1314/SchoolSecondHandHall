package com.javaclimb.shopping.common.config;

import com.javaclimb.shopping.common.constant.Constant;
import com.javaclimb.shopping.common.interceptor.AdminInterceptor;
import com.javaclimb.shopping.common.interceptor.BuyerInterceptor;
import com.javaclimb.shopping.common.interceptor.SellerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/*
前端配置类
 */
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    /*
    配置静态资源访问路径
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:///"+Constant.UPLOADS_PATH);

    }
    /*
    配置jsp访问的前后缀
     */
    @Bean()
    public InternalResourceViewResolver setupViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //管理员
        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/admin/news")
                .addPathPatterns("/admin/user");
        //租客
        registry.addInterceptor(new BuyerInterceptor())
                .addPathPatterns("/admin/profile")
                .addPathPatterns("/admin/order")
                .addPathPatterns("/admin/home")
                .addPathPatterns("/admin/mark")
                .addPathPatterns("/admin/feedback")
                .addPathPatterns("/admin/password");
        //房东
        registry.addInterceptor(new SellerInterceptor())
                .addPathPatterns("/admin/product");
    }
}
