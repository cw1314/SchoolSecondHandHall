package com.javaclimb.shopping.service;

import com.javaclimb.shopping.common.base.BaseService;
import com.javaclimb.shopping.entity.Mark;

import java.util.List;

/**
 * 收藏service接口
 */
public interface MarkService extends BaseService<Mark,Long> {

    /**
     * 根据用户id和房子id查询该用户是否已经收藏过
     */
    List<Mark> findByUserIdAndProductId(Long userId,Long houseId);
}
