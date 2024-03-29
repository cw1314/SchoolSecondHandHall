package com.javaclimb.shopping.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.javaclimb.shopping.common.base.BaseEntity;
import lombok.Data;

/**
 * 用户反馈
 */
@Data
@TableName("t_feedback")
public class FeedBack  extends BaseEntity {
    /**
     * 反馈标题
     */
    private String title;

    /**
     * 反馈内容
     */
    private String content;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 处理状态
     */
    private Integer status;

    /**
     * 回复内容
     */
    private String reply;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人邮箱
     */
    private String contactEmail;
}
