package com.zss.personalspace.repository;

import com.zss.personalspace.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ZSS
 * @date 2019/8/16 19:24
 * @description 博客持久化
 **/
@Repository
public interface BlogRepository extends JpaRepository<Blog, String> {

    /**
     * 分页查询博客
     *
     * @param pageable 分页信息
     * @return Page<Blog>
     */
    Page<Blog> findAll(Pageable pageable);

}
