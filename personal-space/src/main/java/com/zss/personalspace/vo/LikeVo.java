package com.zss.personalspace.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author ZSS
 * @date 2019/8/19 11:00
 * @description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeVo {

    /**
     * 点赞id
     */
    @NotNull
    private String likeId;

    /**
     * 点赞所属
     */
    @NotNull
    private String likeOf;

    /**
     * 点赞数量
     */
    @NotNull
    private Integer likeCount;
}
