package com.zss.personalspace.controller;

import com.zss.personalspace.common.Const;
import com.zss.personalspace.common.ResponseCode;
import com.zss.personalspace.common.ServerResponse;
import com.zss.personalspace.entity.Comment;
import com.zss.personalspace.entity.Like;
import com.zss.personalspace.service.CommentService;
import com.zss.personalspace.service.LikeService;
import com.zss.personalspace.util.UUIDUtil;
import com.zss.personalspace.vo.CommentLikeVo;
import com.zss.personalspace.vo.CommentVo;
import com.zss.personalspace.vo.UserAccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @author ZSS
 * @date 2019/8/19 11:29
 * @description
 */
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;
    private final LikeService likeService;

    @Autowired
    public CommentController(CommentService commentService, LikeService likeService) {
        this.commentService = commentService;
        this.likeService = likeService;
    }

    /**
     * 创建评论
     *
     * @param commentVo 评论实体
     * @param session   session
     * @param result    校验结果
     * @return ServerResponse
     */
    @PostMapping
    public ServerResponse createComment(@Valid @RequestBody CommentVo commentVo, HttpSession session, BindingResult result) {
        UserAccountVo userAccount = (UserAccountVo) session.getAttribute(Const.CURRENT_USER);
        if (result.hasErrors()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAM_ERROR.getCode(), ResponseCode.PARAM_ERROR.getDesc());
        } else if (userAccount == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        } else {
            String commentId = UUIDUtil.getUUID();
            String likeId = UUIDUtil.getUUID();
            try {
                commentService.createComment(Comment.builder()
                        .commentId(commentId)
                        .commentOf(commentVo.getCommentOf())
                        .commentAuthor(userAccount.getUsername())
                        .comment(commentVo.getComment())
                        .headImg(userAccount.getHeadImg())
                        .build());

                likeService.createLike(Like.builder()
                        .likeId(likeId)
                        .likeOf(commentId)
                        .likeCount(Const.INIT_LIKE_COUNT)
                        .build());

                return ServerResponse.createBySuccess();
            } catch (Exception e) {
                e.printStackTrace();
                return ServerResponse.createByError();
            }
        }
    }

    /**
     * 通过评论所属获取评论
     *
     * @param commentOf 评论所属
     * @param session   session
     * @return ServerResponse<List < Comment>>
     */
    @GetMapping("/{commentOf}")
    public ServerResponse<List<CommentLikeVo>> getAllCommentByCommentOf(@PathVariable("commentOf") String commentOf, HttpSession session) {
        UserAccountVo userAccount = (UserAccountVo) session.getAttribute(Const.CURRENT_USER);
        if (userAccount == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        } else if (commentOf == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAM_ERROR.getCode(), ResponseCode.PARAM_ERROR.getDesc());
        } else {
            List<CommentLikeVo> commentLikeVoList = commentService.getAllComment(commentOf);
            if (commentLikeVoList == null) {
                return ServerResponse.createByError();
            } else {
                return ServerResponse.createBySuccess(commentLikeVoList);
            }
        }
    }
}
