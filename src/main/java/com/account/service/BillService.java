package com.account.service;

import com.account.pojo.Bill;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BillService {
    /**
     * 查询所有
     */
    List<Bill> selectAll(Bill bill);

    /**
     * 添加
     */
    Boolean insert(Bill bill);

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
    List<Bill> selectBatch(List<Integer> ids);

    /**
     * 批量删除
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 修改
     */
    void updateById(Bill bill);

    /**
     * 分页查询
     */
    PageInfo<Bill> selectPage(Bill bill, Integer pageNum, Integer pageSize);

    /**
     * 计算好各账单类型的占比和金额
     */
    List<Bill> count(String type);
}
