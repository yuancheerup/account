package com.account.service;

import com.account.pojo.Notice;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface NoticeService {

    /**
     * 查询所有
     */
    List<Notice> selectAll(Notice notice);

    /**
     * 添加
     */
    Boolean insert(Notice notice);

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
    List<Notice> selectBatch(List<Integer> ids);

    /**
     * 批量删除
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 修改
     */
    void updateById(Notice notice);

    /**
     * 分页查询
     */
    PageInfo<Notice> selectPage(Notice notice, Integer pageNum, Integer pageSize);

}
