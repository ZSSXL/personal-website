package com.zss.personalspace.repository;

import com.zss.personalspace.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ZSS
 * @date 2019/8/16 19:22
 * @description 相册持久化
 **/
@Repository
public interface AlbumRepository extends JpaRepository<Album, String> {
}
