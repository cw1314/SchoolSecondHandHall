package com.javaclimb.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaclimb.shopping.entity.FeedBack;
import com.javaclimb.shopping.mapper.FeedBackMapper;
import com.javaclimb.shopping.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 用户反馈service实现类
 */
@Service
public class FeedBackServiceImpl implements FeedBackService {
    @Autowired
    private FeedBackMapper feedBackMapper;


    @Override
    public BaseMapper<FeedBack> getRepository() {
        return feedBackMapper;
    }

    @Override
    public QueryWrapper<FeedBack> getQueryWrapper(FeedBack feedBack) {
        QueryWrapper<FeedBack> queryWrapper = new QueryWrapper<>();
        if(feedBack!=null&&feedBack.getUserId()!=null){
            queryWrapper.eq("user_id",feedBack.getUserId());
        }
        return queryWrapper;
    }

    @Override
    public QueryWrapper<FeedBack> getQueryWrapper(Map<String, Object> condition) {
        return null;
    }
}
