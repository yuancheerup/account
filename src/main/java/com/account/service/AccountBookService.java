package com.account.service;

import com.account.pojo.AccountBook;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AccountBookService {
    /**
     * 查询所有
     */
    List<AccountBook> selectAll(AccountBook accountBook);

    /**
     * 添加
     */
    Boolean insert(AccountBook accountBook);

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
    List<AccountBook> selectBatch(List<Integer> ids);

    /**
     * 批量删除
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 修改
     */
    void updateById(AccountBook accountBook);

    /**
     * 分页查询
     */
    PageInfo<AccountBook> selectPage(AccountBook accountBook, Integer pageNum, Integer pageSize);
}
