package com.zss.personalspace.service;

import com.zss.personalspace.entity.Account;

/**
 * @author ZSS
 * @date 2019/8/17 9:34
 * @description account Service
 **/
public interface AccountService {

    /**
     * 创建账户
     *
     * @param account 账户实体
     * @return Account
     */
    Account createAccount(Account account);

    /**
     * 获取账户信息
     *
     * @param username 用户名
     * @param password 密码
     * @return Account
     */
    Account getAccount(String username, String password);
}
