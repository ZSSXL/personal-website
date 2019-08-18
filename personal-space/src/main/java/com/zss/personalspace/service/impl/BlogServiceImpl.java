package com.zss.personalspace.service.impl;

import com.zss.personalspace.common.Const;
import com.zss.personalspace.entity.Blog;
import com.zss.personalspace.repository.BlogRepository;
import com.zss.personalspace.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author ZSS
 * @date 2019/8/18 9:50
 * @description blog service implements
 */
@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Page<Blog> getAllByPaging(Integer page) {
        return blogRepository.findAll(PageRequest.of(page, Const.DEFAULT_PAGE_SIZE));
    }

    @Override
    public Blog getBlogById(String blogId) {
        return blogRepository.findById(blogId).orElse(null);
    }
}
