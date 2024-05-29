package com.account.mapper;

import com.account.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    // 登录
    User login(String username);

    // 查询某用户名的用户是否存在
    User findUserByUsername(String username);

    // 添加
    void insert(User user);
}
