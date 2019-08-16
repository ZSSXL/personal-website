package com.zss.personalspace.repository;

import com.zss.personalspace.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ZSS
 * @date 2019/8/16 19:24
 * @description 博客持久化
 **/
@Repository
public interface BlogRepository extends JpaRepository<Blog, String> {
}
