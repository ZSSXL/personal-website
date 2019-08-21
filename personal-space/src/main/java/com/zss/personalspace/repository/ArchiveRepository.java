package com.zss.personalspace.repository;

import com.zss.personalspace.entity.Archive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ZSS
 * @date 2019/8/20 18:03
 * @description 归档持久化
 **/
@Repository
public interface ArchiveRepository extends JpaRepository<Archive, String> {

    /**
     * 分页查询归档信息
     *
     * @param pageable 分页信息
     * @return Page<Archive>
     */
    Page<Archive> findByOrderByCreateTimeDesc(Pageable pageable);

}
