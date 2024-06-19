package com.account.service;

import com.account.pojo.Diary;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DiaryService {
    /**
     * 查询所有
     */
    List<Diary> selectAll(Diary diary);

    /**
     * 添加
     */
    Boolean insert(Diary diary);

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
    List<Diary> selectBatch(List<Integer> ids);

    /**
     * 批量删除
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 修改
     */
    void updateById(Diary diary);

    /**
     * 分页查询
     */
    PageInfo<Diary> selectPage(Diary diary, Integer pageNum, Integer pageSize);
}
