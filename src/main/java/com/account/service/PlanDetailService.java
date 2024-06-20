package com.account.service;

import com.account.pojo.PlanDetail;

import java.math.BigDecimal;
import java.util.List;

public interface PlanDetailService {
    /**
     * 查询所有
     */
    List<PlanDetail> selectAll(PlanDetail planDetail);

    /**
     * 添加
     */
    Boolean insert(PlanDetail planDetail);

    /**
     * 根据id查询
     */
    PlanDetail selectById(Integer id);

    /**
     * 根据id删除
     */
    void deleteById(Integer id, Integer planId);

    /**
     * 批量查询
     */
    List<PlanDetail> selectBatch(List<Integer> ids);

    /**
     * 批量删除
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 修改
     */
    void updateById(PlanDetail planDetail);

    /**
     * 分页查询
     */
//    PageInfo<PlanDetail> selectPage(PlanDetail planDetail, Integer pageNum, Integer pageSize);

    BigDecimal getPlanDetailTotal(Integer planId);
}
