package com.zss.personalspace.service;

import com.zss.personalspace.entity.User;

/**
 * @author ZSS
 * @date 2019/8/17 9:25
 * @description user service
 **/
public interface UserService {

    /**
     * 创建用户
     *
     * @param user 用户实体
     * @return User
     */
    User createUser(User user);

    /**
     * 通过用户id获取信息
     *
     * @param userId 用户id
     * @return User
     */
    User getUser(String userId);
}
