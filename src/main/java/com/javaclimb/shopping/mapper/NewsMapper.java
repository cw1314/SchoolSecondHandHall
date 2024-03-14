package com.javaclimb.shopping.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaclimb.shopping.entity.News;
import org.apache.ibatis.annotations.Mapper;

/**
 * 新闻mapper
 */
@Mapper
public interface NewsMapper  extends BaseMapper<News> {
}
