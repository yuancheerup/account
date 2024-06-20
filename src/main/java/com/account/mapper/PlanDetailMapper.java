package com.account.mapper;

import com.account.pojo.PlanDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanDetailMapper {
    /**
     * 查询所有
     */
    List<PlanDetail> selectAll(PlanDetail planDetail);

    /**
     * 添加
     */
    void insert(PlanDetail planDetail);

    /**
     * 根据id查询
     */
    PlanDetail selectById(Integer id);

    /**
     * 根据id, planId删除、修改
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

}
