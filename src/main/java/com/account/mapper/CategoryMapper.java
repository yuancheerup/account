package com.account.mapper;

import com.account.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    /**
     * 查询所有
     */
    List<Category> selectAll(Category category);

    /**
     * 添加
     */
    void insert(Category category);

    /**
     * 根据id查询
     */
    Category selectById(Integer id);

    /**
     * 根据id删除
     */
    void deleteById(Integer id);

    /**
     * 批量查询
     */
    List<Category> selectBatch(List<Integer> ids);

    /**
     * 批量删除
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 修改
     */
    void updateById(Category category);
}
