package com.zss.personalspace.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author ZSS
 * @date 2019/8/18 15:45
 * @description album vo
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlbumVo {

    /**
     * 主题描述
     */
    private String theme;

    /**
     * 相册归类
     */
    @NotNull
    private String type;

}
