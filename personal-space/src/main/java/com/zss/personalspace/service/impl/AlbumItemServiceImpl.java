package com.zss.personalspace.service.impl;

import com.zss.personalspace.entity.AlbumItem;
import com.zss.personalspace.repository.AlbumItemRepository;
import com.zss.personalspace.service.AlbumItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZSS
 * @date 2019/8/18 15:37
 * @description album item service implements
 */
@Service
public class AlbumItemServiceImpl implements AlbumItemService {

    private final AlbumItemRepository albumItemRepository;

    @Autowired
    public AlbumItemServiceImpl(AlbumItemRepository albumItemRepository) {
        this.albumItemRepository = albumItemRepository;
    }

    @Override
    public AlbumItem createAlbumItem(AlbumItem albumItem) {
        return albumItemRepository.save(albumItem);
    }

    @Override
    public AlbumItem getAlbumItemById(String albumId) {
        return albumItemRepository.findById(albumId).orElse(null);
    }
}
