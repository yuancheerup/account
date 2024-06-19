package com.account.service;

import com.account.pojo.Plan;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PlanService {
    /**
     * 查询所有
     */
    List<Plan> selectAll(Plan plan);

    /**
     * 添加
     */
    Boolean insert(Plan plan);

    /**
     * 根据id查询
     */
    Plan selectById(Integer id);

    /**
     * 根据id删除
     */
    void deleteById(Integer id);

    /**
     * 批量查询
     */
    List<Plan> selectBatch(List<Integer> ids);

    /**
     * 批量删除
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 修改
     */
    void updateById(Plan plan);

    /**
     * 分页查询
     */
    PageInfo<Plan> selectPage(Plan plan, Integer pageNum, Integer pageSize);
}
