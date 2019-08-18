package com.zss.personalspace.service;

import com.zss.personalspace.entity.BlogItem;

/**
 * @author ZSS
 * @date 2019/8/18 9:49
 * @description blog item service
 **/
public interface BlogItemService {

    /**
     * 创建博客详情
     *
     * @param blogItem 博客详情实体
     * @return BlogItem
     */
    BlogItem createBlogItem(BlogItem blogItem);

    /**
     * 通过博客id获取博客详情
     *
     * @param blogId 博客id
     * @return BlogItem
     */
    BlogItem getBlogItemById(String blogId);

}
