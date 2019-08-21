package com.zss.personalspace.service.impl;

import com.zss.personalspace.common.Const;
import com.zss.personalspace.entity.Archive;
import com.zss.personalspace.repository.ArchiveRepository;
import com.zss.personalspace.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author ZSS
 * @date 2019/8/20 18:04
 * @description archive service implement
 */
@Service
public class ArchiveServiceImpl implements ArchiveService {

    private final ArchiveRepository archiveRepository;

    @Autowired
    public ArchiveServiceImpl(ArchiveRepository archiveRepository) {
        this.archiveRepository = archiveRepository;
    }

    @Override
    public Archive createArchive(Archive archive) {
        return archiveRepository.save(archive);
    }

    @Override
    public Page<Archive> getAllArchiveByPaging(Integer page) {
        return archiveRepository.findByOrderByCreateTimeDesc(PageRequest.of(page, Const.DEFAULT_PAGE_SIZE));
    }
}
