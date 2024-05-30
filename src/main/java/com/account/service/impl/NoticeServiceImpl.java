package com.account.service.impl;

import com.account.mapper.NoticeMapper;
import com.account.pojo.Notice;
import com.account.service.NoticeService;
import com.account.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
     */
    @Override
    public List<Notice> selectAll(Notice notice) {
        return noticeMapper.selectAll(notice);
    }

    /**
     * 添加
     */
    @Override
    public Boolean insert(Notice notice) {
        // 判断notice是否为null
        if (notice != null) {
            // 获取token
            String[] strings = TokenUtils.decodeToken();

            // token不为空，并且是管理员
            if (strings != null && strings[1].equals("ADMIN")) {
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

    /**
     * 根据
     */
    @Override
    public boolean selectById(Integer id) {
        Notice notice = noticeMapper.selectById(id);
        return notice != null;
    }

    /**
     * 根据id删除
     */
    @Override
    public void deleteById(Integer id) {
        noticeMapper.deleteById(id);
    }

    /**
     * 批量查询
     */
    @Override
    public List<Notice> selectBatch(List<Integer> ids) {
        return noticeMapper.selectBatch(ids);
    }

    /**
     * 批量删除
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        noticeMapper.deleteBatch(ids);
    }

    /**
     * 修改
     */
    @Override
    public void updateById(Notice notice) {
        noticeMapper.updateById(notice);
    }

    /**
     * 分页查询
     */
    @Override
    public PageInfo<Notice> selectPage(Notice notice, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Notice> noticeList = noticeMapper.selectAll(notice);
        log.info("分页查询结果：{}", PageInfo.of(noticeList));
        return PageInfo.of(noticeList);
    }


}
