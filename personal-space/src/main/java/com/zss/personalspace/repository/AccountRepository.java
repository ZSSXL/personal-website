package com.zss.personalspace.repository;

import com.zss.personalspace.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ZSS
 * @date 2019/8/16 19:21
 * @description 账户持久化
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
}
