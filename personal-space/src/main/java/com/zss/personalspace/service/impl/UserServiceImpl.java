package com.zss.personalspace.service.impl;

import com.zss.personalspace.entity.User;
import com.zss.personalspace.repository.UserRepository;
import com.zss.personalspace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZSS
 * @date 2019/8/17 9:26
 * @description user service implement
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
