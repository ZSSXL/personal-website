package com.zss.personalspace.repository;

import com.zss.personalspace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ZSS
 * @date 2019/8/17 8:39
 * @description 用户持久化
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
