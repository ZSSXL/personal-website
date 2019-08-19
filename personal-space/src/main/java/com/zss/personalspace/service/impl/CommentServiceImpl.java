package com.zss.personalspace.service.impl;

import com.zss.personalspace.entity.Comment;
import com.zss.personalspace.entity.Like;
import com.zss.personalspace.repository.CommentRepository;
import com.zss.personalspace.repository.LikeRepository;
import com.zss.personalspace.service.CommentService;
import com.zss.personalspace.vo.CommentLikeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author ZSS
 * @date 2019/8/19 11:25
 * @description comment service implements
 */
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, LikeRepository likeRepository) {
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<CommentLikeVo> getAllComment(String commentOf) {
        List<Comment> commentList = commentRepository.findAllByCommentOfOrderByCreateTimeAsc(commentOf);
        if (commentList == null) {
            return null;
        } else {
            List<CommentLikeVo> commentLikeVoList = new ArrayList<>();
            for (Comment com : commentList) {
                Optional<Like> optional = likeRepository.findByLikeOf(com.getCommentId());
                if(optional.isPresent()){
                    CommentLikeVo build = CommentLikeVo.builder()
                            .commentId(com.getCommentId())
                            .commentAuthor(com.getCommentAuthor())
                            .commentOf(com.getCommentOf())
                            .comment(com.getComment())
                            .headImg(com.getHeadImg())
                            .likeId(optional.get().getLikeId())
                            .likeCount(optional.get().getLikeCount())
                            .createTime(com.getCreateTime())
                            .build();
                    commentLikeVoList.add(build);
                }
            }
            return commentLikeVoList;
        }
    }
}
