package com.account.service;

import com.account.pojo.Category;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CategoryService {
    /**
     * 查询所有
     */
    List<Category> selectAll(Category category);

    /**
     * 添加
     */
    Boolean insert(Category category);

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
    List<Category> selectBatch(List<Integer> ids);

    /**
     * 批量删除
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 修改
     */
    void updateById(Category category);

    /**
     * 分页查询
     */
    PageInfo<Category> selectPage(Category category, Integer pageNum, Integer pageSize);
}
