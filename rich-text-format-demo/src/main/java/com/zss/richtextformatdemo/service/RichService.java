package com.zss.richtextformatdemo.service;

import com.zss.richtextformatdemo.entity.RichText;

/**
 * @author ZSS
 * @date 2019/8/11 10:37
 * @description 富文本 service
 **/
public interface RichService {

    /**
     * 保存富文本内容
     *
     * @param richText 富文本实体
     * @return RichText
     */
    RichText createRichText(RichText richText);

    /**
     * 通过id获取富文本内容
     *
     * @param richId id
     * @return RichText
     */
    RichText getRichText(String richId);

}
