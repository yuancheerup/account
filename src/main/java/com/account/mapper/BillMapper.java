package com.account.mapper;

import com.account.pojo.Bill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BillMapper {
    /**
     * 查询所有
     */
    List<Bill> selectAll(Bill bill);

    /**
     * 添加
     */
    void insert(Bill bill);

    /**
     * 根据id查询
     */
    Bill selectById(Integer id);

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
     * 查询所有分类
     */
    List<String> selectCategoryByType(String type);

    /**
     * 根据账本id查询
     */
    List<Bill> selectByBookId(Integer bookId);
}
