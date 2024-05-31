package com.account.mapper;

import com.account.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    /**
     * 添加
     */
    void insert(Admin admin);

    /**
     * 根据用户名查询
     */
    Admin selectByUsername(String username);

    /**
     * 根据id查询
     */
    Admin selectById(Integer id);

    /**
     * 根据id删除
     */
    void deleteById(Integer id);

    /**
     * 批量查询
     */
    List<Admin> selectBatch(List<Integer> ids);

    /**
     * 批量删除
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 修改
     */
    void update(Admin admin);

    /**
     * 查询（条件查询）
     */
    List<Admin> selectAll(Admin admin);
}
