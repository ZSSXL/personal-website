package com.zss.personalspace.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZSS
 * @date 2019/8/18 16:40
 * @description album detail vo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDetailVo {

    /**
     * 相册id
     */
    private String albumId;

    /**
     * 发布者
     */
    private String publish;

    /**
     * 封面图片
     */
    private String coverImg;

    /**
     * 主题描述
     */
    private String theme;

    /**
     * 相册归类
     */
    private String type;
    /**
     * 图片
     */
    private String photos;

    /**
     * 创建日期
     */
    private Long createTime;

}
