package com.zss.personalspace.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZSS
 * @date 2019/8/18 12:24
 * @description blog detail vo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogDetailVo {

    /**
     * 博客id
     */
    private String blogId;

    /**
     * 作者
     */
    private String author;

    /**
     * 封面图片
     */
    private String coverImg;

    /**
     * 博客主题
     */
    private String theme;

    /**
     * 博客内容
     */
    private String content;

    /**
     * 创建日期
     */
    private Long createTime;
}
