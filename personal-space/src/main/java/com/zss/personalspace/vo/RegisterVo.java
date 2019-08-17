package com.zss.personalspace.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


/**
 * @author ZSS
 * @date 2019/8/17 9:26
 * @description register vo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterVo {

    /**
     * 用户密码
     */
    @NotBlank
    private String password;

    /**
     * 用户名
     */
    @NotBlank
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
    @NotBlank
    private String mood;

    /**
     * 头像
     */
    private String headImg;

}
