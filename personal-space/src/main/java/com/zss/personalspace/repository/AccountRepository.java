package com.zss.personalspace.repository;

import com.zss.personalspace.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ZSS
 * @date 2019/8/16 19:21
 * @description 账户持久化
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    /**
     * 通过用户名和密码确认用户
     *
     * @param username 用户名
     * @param password 密码
     * @return Account
     */
    Optional<Account> findByUsernameAndPassword(String username, String password);

}
