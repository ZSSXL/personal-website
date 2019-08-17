package com.zss.personalspace.repository;

import com.zss.personalspace.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ZSS
 * @date 2019/8/17 8:37
 * @description 评论持久化
 **/
@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
}
