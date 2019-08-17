package com.zss.personalspace.repository;

import com.zss.personalspace.entity.BlogItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ZSS
 * @date 2019/8/17 8:36
 * @description 博客详情持久化
 **/
@Repository
public interface BlogItemRepository extends JpaRepository<BlogItem, String> {
}
