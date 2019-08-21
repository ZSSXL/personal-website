package com.zss.personalspace.service;

import com.zss.personalspace.entity.Archive;
import org.springframework.data.domain.Page;

/**
 * @author ZSS
 * @date 2019/8/20 18:04
 * @description archive service
 **/
public interface ArchiveService {

    /**
     * 创建归档
     *
     * @param archive 归档实体
     * @return Archive
     */
    Archive createArchive(Archive archive);

    /**
     * 分页查询归档
     *
     * @param page 当前页
     * @return Page<Archive>
     */
    Page<Archive> getAllArchiveByPaging(Integer page);

}
