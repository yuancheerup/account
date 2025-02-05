package com.account.mapper;

import com.account.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 根据用户名查询
     */
    User selectUserByUsername(String username);

    /**
     * 添加
     */
    void insert(User user);

    /**
     * 根据id查询
     */
    User selectById(Integer id);

    /**
     * 根据id删除
     */
    void deleteById(Integer id);

    /**
     * 批量查询
     */
    List<User> selectBatch(List<Integer> ids);

    /**
     * 批量删除
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 修改
     */
    void update(User user);

    /**
     * 查询（条件查询）
     */
    List<User> selectAll(User user);
}
