package com.zss.personalspace.service;

import com.zss.personalspace.entity.Blog;
import org.springframework.data.domain.Page;

/**
 * @author ZSS
 * @date 2019/8/18 9:49
 * @description blog service
 **/
public interface BlogService {

    /**
     * 创建博客
     *
     * @param blog blog实体
     * @return Blog
     */
    Blog createBlog(Blog blog);

    /**
     * 分页查询blog
     *
     * @param page 页数
     * @return Page<Blog>
     */
    Page<Blog> getAllByPaging(Integer page);

    /**
     * 通过blogId获取信息
     *
     * @param blogId 博客id
     * @return Blog
     */
    Blog getBlogById(String blogId);
}
