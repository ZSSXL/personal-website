package com.zss.richtextformatdemo.repository;

import com.zss.richtextformatdemo.entity.RichText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ZSS
 * @date 2019/8/10 21:58
 * @description 富文本实体持久化
 */
@Repository
public interface RichTextRepository extends JpaRepository<RichText, String> {

}
