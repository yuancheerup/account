package com.account.mapper;

import com.account.pojo.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {
    /**
     * 查询所有
     */
    List<Account> selectAll(Account account);

    /**
     * 添加
     */
    void insert(Account account);

    /**
     * 根据id查询
     */
    Account selectById(Integer id);

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
    
}
