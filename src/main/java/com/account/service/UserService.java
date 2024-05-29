package com.account.service;

import com.account.pojo.User;

import java.util.List;

public interface UserService {
    /**
     * 用户登录
     */
    User login(User user);

    /**
     * 用户注册
     */
    Boolean register(User user);
}
