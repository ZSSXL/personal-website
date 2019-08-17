package com.zss.personalspace.repository;

import com.zss.personalspace.entity.AlbumItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ZSS
 * @date 2019/8/16 19:23
 * @description 相册详情持久化
 **/
@Repository
public interface AlbumItemRepository extends JpaRepository<AlbumItem, String> {
}
