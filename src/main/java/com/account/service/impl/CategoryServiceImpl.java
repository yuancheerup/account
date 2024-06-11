package com.account.service.impl;

import com.account.mapper.CategoryMapper;
import com.account.pojo.Category;
import com.account.service.CategoryService;
import com.account.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    
    /**
     * 查询所有
     */
    @Override
    public List<Category> selectAll(Category category) {
        return categoryMapper.selectAll(category);
    }

    /**
     * 添加
     */
    @Override
    public Boolean insert(Category category) {
        // 判断category是否为null
        if (category != null) {
            // 获取token
            String[] strings = TokenUtils.decodeToken();

            // token不为空，并且是管理员
            if (strings != null && strings[1].equals("ADMIN")) {
                log.info("category insert service: {}", Arrays.toString(strings));
                categoryMapper.insert(category);
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
        Category category = categoryMapper.selectById(id);
        return category != null;
    }

    /**
     * 根据id删除
     */
    @Override
    public void deleteById(Integer id) {
        categoryMapper.deleteById(id);
    }

    /**
     * 批量查询
     */
    @Override
    public List<Category> selectBatch(List<Integer> ids) {
        return categoryMapper.selectBatch(ids);
    }

    /**
     * 批量删除
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        categoryMapper.deleteBatch(ids);
    }

    /**
     * 修改
     */
    @Override
    public void updateById(Category category) {
        categoryMapper.updateById(category);
    }

    /**
     * 分页查询
     */
    @Override
    public PageInfo<Category> selectPage(Category category, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Category> categoryList = categoryMapper.selectAll(category);
        log.info("分页查询结果：{}", PageInfo.of(categoryList));
        return PageInfo.of(categoryList);
    }
}
