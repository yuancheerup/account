package com.account.service;

import com.account.pojo.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    /**
     * 用户登录
     */
    User login(User user);

    /**
     * 用户注册
     */
    void register(User user);

    /**
     * 查询所有
     */
    List<User> selectAll(User user);

    /**
     * 添加
     */
    Boolean insert(User user);

    /**
     * 根据用户名查询
     */
    User selectUserByUsername(String username);

    /**
     * 根据id查询
     */
    boolean selectById(Integer id);

    /**
     * 根据id删除
     */
    void deleteById(Integer id);

    /**
     * 批量查询
     */
    List<User> selectBatch(List<Integer> ids);

    /**
     * 批量删除
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 修改
     */
    void update(User user);

    /**
     * 分页查询
     */
    PageInfo<User> selectPage(User user, Integer pageNum, Integer pageSize);

    /**
     * 修改密码
     */
    Boolean updatePassword(Integer id, String password, String newPassword);
}
