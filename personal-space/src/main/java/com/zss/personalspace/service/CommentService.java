package com.zss.personalspace.service;

import com.zss.personalspace.entity.Comment;
import com.zss.personalspace.vo.CommentLikeVo;

import java.util.List;

/**
 * @author ZSS
 * @date 2019/8/19 11:24
 * @description Comment service
 **/
public interface CommentService {

    /**
     * 创建评论
     *
     * @param comment 评论实体
     * @return Comment
     */
    Comment createComment(Comment comment);

    /**
     * 通过评论所属查询评论
     *
     * @param commentOf 评论所属
     * @return List<CommentLikeVo>
     */
    List<CommentLikeVo> getAllComment(String commentOf);

}
