package com.account.mapper;

import com.account.pojo.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    /**
     * 查询所有
     * @return
     */
    List<Notice> selectAll();

    /**
     * 添加
     * @param notice
     */
    void insert(Notice notice);
}
