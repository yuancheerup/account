package com.account.service;

import com.account.pojo.Account;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AccountService {
    
    /**
     * 查询所有
     */
    List<Account> selectAll(Account account);

    /**
     * 添加
     */
    Boolean insert(Account account);

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
    List<Account> selectBatch(List<Integer> ids);

    /**
     * 批量删除
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 修改
     */
//    void updateById(Account account);

    /**
     * 分页查询
     */
    PageInfo<Account> selectPage(Account account, Integer pageNum, Integer pageSize);

}
