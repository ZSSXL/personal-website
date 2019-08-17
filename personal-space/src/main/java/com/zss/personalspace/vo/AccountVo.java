package com.zss.personalspace.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author ZSS
 * @date 2019/8/17 10:28
 * @description account vo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NotNull
public class AccountVo {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}
