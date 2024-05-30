package com.account.mapper;

import com.account.pojo.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    /**
     * 查询所有
     */
    List<Notice> selectAll(Notice notice);

    /**
     * 添加
     */
    void insert(Notice notice);

    /**
     * 根据id查询
     */
    Notice selectById(Integer id);

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
}
