package com.zss.personalspace.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZSS
 * @date 2019/8/18 9:56
 * @description blog vo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogVo {

    /**
     * 博客主题
     */
    private String theme;

    /**
     * 博客内容
     */
    private String content;


}
