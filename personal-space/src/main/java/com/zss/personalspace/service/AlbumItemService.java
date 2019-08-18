package com.zss.personalspace.service;

import com.zss.personalspace.entity.AlbumItem;

/**
 * @author ZSS
 * @date 2019/8/18 15:36
 * @description album item service
 **/
public interface AlbumItemService {

    /**
     * 新建相册详情
     *
     * @param albumItem 相册详情实体
     * @return AlbumItem
     */
    AlbumItem createAlbumItem(AlbumItem albumItem);

    /**
     * 通过id获取相册详情
     *
     * @param albumId 相册id
     * @return AlbumItem
     */
    AlbumItem getAlbumItemById(String albumId);

}
