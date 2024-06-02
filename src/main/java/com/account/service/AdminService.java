package com.account.service;

import com.account.pojo.Admin;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AdminService {

    /**
     * 查询所有
     */
    List<Admin> selectAll(Admin admin);

    /**
     * 登录
     */
    Admin login(Admin admin);

    /**
     * 添加
     */
    Boolean insert(Admin admin);

    /**
     * 根据用户名查询
     */
    Admin selectByUsername(String username);

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
    List<Admin> selectBatch(List<Integer> ids);

    /**
     * 批量删除
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 修改
     */
    void update(Admin admin);

    /**
     * 分页查询
     */
    PageInfo<Admin> selectPage(Admin admin, Integer pageNum, Integer pageSize);

    /**
     * 修改密码
     */
    Boolean updatePassword(Integer id, String password, String newPassword);

}
