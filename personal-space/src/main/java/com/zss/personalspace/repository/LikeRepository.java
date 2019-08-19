package com.zss.personalspace.repository;

import com.zss.personalspace.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ZSS
 * @date 2019/8/17 8:38
 * @description 点赞持久化
 **/
@Repository
public interface LikeRepository extends JpaRepository<Like, String> {

    /**
     * 通过所属id查询到点赞信息
     *
     * @param ofId 所属id
     * @return Optional<Like>
     */
    Optional<Like> findByLikeOf(String ofId);

}
