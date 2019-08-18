package com.zss.personalspace.service;

import com.zss.personalspace.entity.Album;
import org.springframework.data.domain.Page;

/**
 * @author ZSS
 * @date 2019/8/18 15:34
 * @description album service
 **/
public interface AlbumService {

    /**
     * 添加相册
     *
     * @param album 相册实体
     * @return Album
     */
    Album createAlbum(Album album);

    /**
     * 分页获取相册
     *
     * @param page 当前页
     * @return Page<Album>
     */
    Page<Album> getAllAlbumByPaging(Integer page);

    /**
     * 通过id获取相册
     *
     * @param albumId 相册id
     * @return Album
     */
    Album getAlbumById(String albumId);

}
