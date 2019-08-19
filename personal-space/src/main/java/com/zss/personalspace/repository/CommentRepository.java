package com.zss.personalspace.repository;

import com.zss.personalspace.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZSS
 * @date 2019/8/17 8:37
 * @description 评论持久化
 **/
@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {

    /**
     * 通过评论所属查询评论
     *
     * @param commentOf 评论所属
     * @return List<Comment>
     */
    List<Comment> findAllByCommentOfOrderByCreateTimeAsc(String commentOf);

}
