package com.account.mapper;

import com.account.pojo.Plan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanMapper {
    /**
     * 查询所有
     */
    List<Plan> selectAll(Plan plan);

    /**
     * 添加
     */
    void insert(Plan plan);

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
}
