package com.zss.personalspace.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author ZSS
 * @date 2019/8/19 13:04
 * @description
 */
@Data
@Builder
public class CommentLikeVo {

    /**
     * 评论id
     */
    private String commentId;

    /**
     * 评论所属
     */
    private String commentOf;

    /**
     * 评论者
     */
    private String commentAuthor;

    /**
     * 作者头像
     */
    private String headImg;

    /**
     * 评论内容
     */
    private String comment;

    /**
     * 点赞id
     */
    private String likeId;

    /**
     * 点赞数量
     */
    private Integer likeCount;

    /**
     * 创建时间
     */
    private Long createTime;

}
