package com.account.mapper;

import com.account.pojo.Diary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiaryMapper {
    /**
     * 查询所有
     */
    List<Diary> selectAll(Diary diary);

    /**
     * 添加
     */
    void insert(Diary diary);

    /**
     * 根据id查询
     */
    Diary selectById(Integer id);

    /**
     * 根据id删除
     */
    void deleteById(Integer id);

    /**
     * 批量查询
     */
    List<Diary> selectBatch(List<Integer> ids);

    /**
     * 批量删除
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 修改
     */
    void updateById(Diary diary);
}
