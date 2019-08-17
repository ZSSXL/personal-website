package com.zss.personalspace.service.impl;

import com.zss.personalspace.entity.Account;
import com.zss.personalspace.repository.AccountRepository;
import com.zss.personalspace.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZSS
 * @date 2019/8/17 9:35
 * @description account service implement
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account getAccount(String username, String password) {
        return accountRepository.findByUsernameAndPassword(username, password).orElse(null);
    }
}
