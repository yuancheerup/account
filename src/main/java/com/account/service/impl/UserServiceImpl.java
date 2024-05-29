package com.account.service.impl;

import com.account.mapper.UserMapper;
import com.account.pojo.User;
import com.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 登录
     */
    @Override
    public User login(User user) {
        if (user.getUsername() == null) {
            return null;
        }
        User u = userMapper.login(user.getUsername());
        if (u!= null && u.getPassword().equals(user.getPassword())) {
            return u;
        }
        return null;
    }

    /**
     * 注册
     */
    @Override
    public Boolean register(User user) {
        User userByUsername = userMapper.findUserByUsername(user.getUsername());
        if (userByUsername == null) {
            userMapper.insert(user);
            return true;
        }
        return false;
    }
}
