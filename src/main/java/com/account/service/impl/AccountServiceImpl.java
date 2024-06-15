package com.account.service.impl;

import com.account.mapper.AccountMapper;
import com.account.pojo.Account;
import com.account.service.AccountService;
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
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;
    /**
     * 查询所有
     */
    @Override
    public List<Account> selectAll(Account account) {
        return accountMapper.selectAll(account);
    }

    /**
     * 添加
     */
    @Override
    public Boolean insert(Account account) {
        // 判断account是否为null
        if (account != null) {
            // 获取token
            String[] strings = TokenUtils.decodeToken();

            // token不为空，并且是管理员
            if (strings != null && strings[1].equals("ADMIN")) {
                log.info("account insert service: {}", Arrays.toString(strings));
                accountMapper.insert(account);
                return true;
            }
            log.info(Arrays.toString(strings));
        }
        return false;
    }

    /**
     * 根据id查询
     */
    @Override
    public boolean selectById(Integer id) {
        Account account = accountMapper.selectById(id);
        return account != null;
    }

    /**
     * 根据id删除
     */
    @Override
    public void deleteById(Integer id) {
        accountMapper.deleteById(id);
    }

    /**
     * 批量查询
     */
    @Override
    public List<Account> selectBatch(List<Integer> ids) {
        return accountMapper.selectBatch(ids);
    }

    /**
     * 批量删除
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        accountMapper.deleteBatch(ids);
    }

    /**
     * 修改
     */
//    @Override
//    public void updateById(Account account) {
//        accountMapper.updateById(account);
//    }

    /**
     * 分页查询
     */
    @Override
    public PageInfo<Account> selectPage(Account account, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Account> accountList = accountMapper.selectAll(account);
        log.info("分页查询结果：{}", PageInfo.of(accountList));
        return PageInfo.of(accountList);
    }
    
}
