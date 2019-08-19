package com.zss.personalspace.controller;

import com.zss.personalspace.common.Const;
import com.zss.personalspace.common.ResponseCode;
import com.zss.personalspace.common.ServerResponse;
import com.zss.personalspace.entity.Like;
import com.zss.personalspace.service.LikeService;
import com.zss.personalspace.vo.LikeVo;
import com.zss.personalspace.vo.UserAccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author ZSS
 * @date 2019/8/19 9:32
 * @description like controller
 */
@RestController
@RequestMapping("/api/like")
public class LikeController {

    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    /**
     * 点赞加一
     *
     * @param likeVo  like vo
     * @param session session
     * @return ServerResponse
     */
    @PutMapping
    public ServerResponse addLike(@Valid @RequestBody LikeVo likeVo, HttpSession session, BindingResult result) {
        UserAccountVo userAccountVo = (UserAccountVo) session.getAttribute(Const.CURRENT_USER);
        if (userAccountVo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        } else if (result.hasErrors()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAM_ERROR.getCode(), ResponseCode.PARAM_ERROR.getDesc());
        } else {
            try {
                likeService.createLike(Like.builder()
                        .likeId(likeVo.getLikeId())
                        .likeOf(likeVo.getLikeOf())
                        .likeCount(likeVo.getLikeCount() + 1)
                        .build());
                return ServerResponse.createBySuccess();
            } catch (Exception e) {
                e.printStackTrace();
                return ServerResponse.createByError();
            }
        }
    }
}
