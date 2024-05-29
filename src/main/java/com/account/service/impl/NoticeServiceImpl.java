package com.account.service.impl;

import com.account.mapper.NoticeMapper;
import com.account.pojo.Notice;
import com.account.service.NoticeService;
import com.account.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;
    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Notice> selectAll() {
       List<Notice> noticeList =  noticeMapper.selectAll();
       return noticeList;
    }

    /**
     * 添加
     * @param notice
     * @return
     */
    @Override
    public Boolean insert(Notice notice) {
        // 判断notice是否为null
        if (notice != null) {
            // 获取token
            String[] strings = TokenUtils.decodeToken();

            // token不为空，并且是管理员
            if (strings != null && strings[1].equals("USER")) {
                log.info("notice insert service: {}", Arrays.toString(strings));
                notice.setCreateTime(LocalDateTime.now());
                notice.setCreateUser(strings[2]);
                noticeMapper.insert(notice);
                return true;
            }
            log.info(Arrays.toString(strings));
        }
        return false;
    }
}
