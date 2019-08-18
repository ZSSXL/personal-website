package com.zss.personalspace.service.impl;

import com.zss.personalspace.entity.BlogItem;
import com.zss.personalspace.repository.BlogItemRepository;
import com.zss.personalspace.service.BlogItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZSS
 * @date 2019/8/18 9:50
 * @description blog item service implements
 */
@Service
public class BlogItemServiceImpl implements BlogItemService {

    private final BlogItemRepository blogItemRepository;

    @Autowired
    public BlogItemServiceImpl(BlogItemRepository blogItemRepository) {
        this.blogItemRepository = blogItemRepository;
    }

    @Override
    public BlogItem createBlogItem(BlogItem blogItem) {
        return blogItemRepository.save(blogItem);
    }

    @Override
    public BlogItem getBlogItemById(String blogId) {
        return blogItemRepository.findById(blogId).orElse(null);
    }
}
