package com.zss.personalspace.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author ZSS
 * @date 2019/8/17 10:56
 * @description
 */
@Data
@Builder
public class UserAccountVo {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 出生年月日
     */
    private String birthday;

    /**
     * 现在居住地
     */
    private String address;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 心情
     */
    private String mood;

    /**
     * 头像
     */
    private String headImg;
}
