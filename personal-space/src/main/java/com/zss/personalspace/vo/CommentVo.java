package com.zss.personalspace.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author ZSS
 * @date 2019/8/19 11:30
 * @description comment vo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentVo {

    /**
     * 评论所属
     */
    @NotNull
    private String commentOf;

    /**
     * 评论内容
     */
    @NotNull
    private String comment;

}
