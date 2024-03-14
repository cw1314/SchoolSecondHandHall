package com.javaclimb.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaclimb.shopping.entity.Mark;
import com.javaclimb.shopping.mapper.MarkMapper;
import com.javaclimb.shopping.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 收藏实现类
 *
 */
@Service
public class MarkServiceImpl implements MarkService {
    @Autowired
    private MarkMapper markMapper;

    /**
     * 根据用户id和房子id查询该用户是否已经收藏过该房子

     */
    @Override
    public List<Mark> findByUserIdAndProductId(Long userId, Long productId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("product_id",productId);
        return markMapper.selectList(queryWrapper);
    }
    /*
    mapper对象
     */
    @Override
    public BaseMapper<Mark> getRepository() {
        return markMapper;
    }

    /**
     * 获得查询器
     * @param mark
     * @return
     */
    @Override
    public QueryWrapper<Mark> getQueryWrapper(Mark mark) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if(mark!=null){
            if(mark.getUserId()!=null){
                queryWrapper.eq("user_id",mark.getUserId());
            }
            if(mark.getProductId()!=null){
                queryWrapper.eq("product_id",mark.getProductId());
            }
        }
        return queryWrapper;
    }
/*
获取带参数的查询器
 */
    @Override
    public QueryWrapper<Mark> getQueryWrapper(Map<String, Object> condition) {
        return null;
    }
}
