package com.account.mapper;

import com.account.pojo.AccountBook;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountBookMapper {
    /**
     * 查询所有
     */
    List<AccountBook> selectAll(AccountBook accountBook);

    /**
     * 添加
     */
    void insert(AccountBook accountBook);

    /**
     * 根据id查询
     */
    AccountBook selectById(Integer id);

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
}
