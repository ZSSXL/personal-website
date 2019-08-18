package com.zss.personalspace.service;

import com.zss.personalspace.entity.Like;

/**
 * @author ZSS
 * @date 2019/8/18 21:47
 * @description like service
 **/
public interface LikeService {

    /**
     * 创建点赞
     *
     * @param like 点赞实体
     * @return Like
     */
    Like createLike(Like like);

}
