package com.account.service.impl;

import com.account.mapper.AccountBookMapper;
import com.account.pojo.AccountBook;
import com.account.pojo.Plan;
import com.account.service.AccountBookService;
import com.account.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class AccountBookServiceImpl implements AccountBookService {
    @Autowired
    private AccountBookMapper accountBookMapper;

    /**
     * 查询所有
     */
    @Override
    public List<AccountBook> selectAll(AccountBook accountBook) {
        return accountBookMapper.selectAll(accountBook);
    }

    /**
     * 添加
     */
    @Override
    public Boolean insert(AccountBook accountBook) {
        String[] data = TokenUtils.decodeToken();

        // 判断accountBook是否为null
        if (accountBook != null) {
            // 同一个用户添加的账本名不能相同
            List<AccountBook> accountBooks = accountBookMapper.selectAll(null);
            for (AccountBook ab : accountBooks) {
                if (ab.getName().equals(accountBook.getName()) &&
                        ab.getUserId() == Integer.parseInt(data[0])) {
                    return false;
                }
            }

            // 设置创建时间
            accountBook.setCreateTime(LocalDateTime.now());
            accountBookMapper.insert(accountBook);
            return true;
        }
        return false;
    }

    /**
     * 根据
     */
    @Override
    public boolean selectById(Integer id) {
        AccountBook accountBook = accountBookMapper.selectById(id);
        return accountBook != null;
    }

    /**
     * 根据id删除
     */
    @Override
    public void deleteById(Integer id) {
        accountBookMapper.deleteById(id);
    }

    /**
     * 批量查询
     */
    @Override
    public List<AccountBook> selectBatch(List<Integer> ids) {
        return accountBookMapper.selectBatch(ids);
    }

    /**
     * 批量删除
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        accountBookMapper.deleteBatch(ids);
    }

    /**
     * 修改
     */
    @Override
    public void updateById(AccountBook accountBook) {
        accountBookMapper.updateById(accountBook);
    }

    /**
     * 分页查询
     */
    @Override
    public PageInfo<AccountBook> selectPage(AccountBook accountBook, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AccountBook> accountBookList = accountBookMapper.selectAll(accountBook);
        log.info("分页查询结果：{}", PageInfo.of(accountBookList));
        return PageInfo.of(accountBookList);
    }
}
