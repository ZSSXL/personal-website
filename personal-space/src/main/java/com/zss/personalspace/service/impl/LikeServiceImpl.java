package com.zss.personalspace.service.impl;

import com.zss.personalspace.entity.Like;
import com.zss.personalspace.repository.LikeRepository;
import com.zss.personalspace.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZSS
 * @date 2019/8/18 21:48
 * @description like service implement
 */
@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public Like createLike(Like like) {
        return likeRepository.save(like);
    }

    @Override
    public Like getLikeByLikeOf(String ofId) {
        return likeRepository.findByLikeOf(ofId).orElse(null);
    }
}
