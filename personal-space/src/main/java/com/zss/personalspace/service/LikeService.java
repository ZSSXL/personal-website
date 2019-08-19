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

    /**
     * 通过所属id查询点赞信息
     *
     * @param ofId 所属id
     * @return Like
     */
    Like getLikeByLikeOf(String ofId);

}
