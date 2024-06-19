package com.account.service.impl;

import com.account.mapper.DiaryMapper;
import com.account.pojo.Diary;
import com.account.service.DiaryService;
import com.account.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class DiaryServiceImpl implements DiaryService {
    @Autowired
    private DiaryMapper diaryMapper;

    /**
     * 查询所有
     */
    @Override
    public List<Diary> selectAll(Diary diary) {
        return diaryMapper.selectAll(diary);
    }

    /**
     * 添加
     */
    @Override
    public Boolean insert(Diary diary) {
        // 判断diary是否为null
        if (diary != null) {
            // 设置创建时间
            diary.setCreateTime(LocalDateTime.now());
            diaryMapper.insert(diary);
            return true;
        }
        return false;
    }

    /**
     * 根据
     */
    @Override
    public boolean selectById(Integer id) {
        Diary diary = diaryMapper.selectById(id);
        return diary != null;
    }

    /**
     * 根据id删除
     */
    @Override
    public void deleteById(Integer id) {
        diaryMapper.deleteById(id);
    }

    /**
     * 批量查询
     */
    @Override
    public List<Diary> selectBatch(List<Integer> ids) {
        return diaryMapper.selectBatch(ids);
    }

    /**
     * 批量删除
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        diaryMapper.deleteBatch(ids);
    }

    /**
     * 修改
     */
    @Override
    public void updateById(Diary diary) {
        diaryMapper.updateById(diary);
    }

    /**
     * 分页查询
     */
    @Override
    public PageInfo<Diary> selectPage(Diary diary, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Diary> diaryList = diaryMapper.selectAll(diary);
        log.info("分页查询结果：{}", PageInfo.of(diaryList));
        return PageInfo.of(diaryList);
    }
}
