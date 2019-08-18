package com.zss.personalspace.service.impl;

import com.zss.personalspace.common.Const;
import com.zss.personalspace.entity.Album;
import com.zss.personalspace.repository.AlbumRepository;
import com.zss.personalspace.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author ZSS
 * @date 2019/8/18 15:35
 * @description album service implements
 */
@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public Album createAlbum(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public Page<Album> getAllAlbumByPaging(Integer page) {
        return albumRepository.findAll(PageRequest.of(page, Const.DEFAULT_PAGE_SIZE));
    }

    @Override
    public Album getAlbumById(String albumId) {
        return albumRepository.findById(albumId).orElse(null);
    }
}
