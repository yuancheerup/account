package com.account.service;

import com.account.pojo.Notice;

import java.util.List;

public interface NoticeService {

    /**
     * 查询所有
     * @return
     */
    List<Notice> selectAll();

    /**
     * 添加
     * @param notice
     * @return
     */
    Boolean insert(Notice notice);
}
