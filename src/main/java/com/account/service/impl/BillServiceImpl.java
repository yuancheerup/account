package com.account.service.impl;

import com.account.mapper.BillMapper;
import com.account.pojo.Bill;
import com.account.service.BillService;
import com.account.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillMapper billMapper;

    /**
     * 查询所有
     */
    @Override
    public List<Bill> selectAll(Bill bill) {
        return billMapper.selectAll(bill);
    }

    /**
     * 添加
     */
    @Override
    public Boolean insert(Bill bill) {
        // 判断bill是否为null
        if (bill != null) {
            // 获取token
            String[] strings = TokenUtils.decodeToken();
            log.info("bill insert service: {}", Arrays.toString(strings));
            // 设置创建时间
            bill.setCreateTime(LocalDateTime.now());
            billMapper.insert(bill);
            return true;
        }
        return false;
    }

    /**
     * 根据
     */
    @Override
    public boolean selectById(Integer id) {
        Bill bill = billMapper.selectById(id);
        return bill != null;
    }

    /**
     * 根据id删除
     */
    @Override
    public void deleteById(Integer id) {
        billMapper.deleteById(id);
    }

    /**
     * 批量查询
     */
    @Override
    public List<Bill> selectBatch(List<Integer> ids) {
        return billMapper.selectBatch(ids);
    }

    /**
     * 批量删除
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        billMapper.deleteBatch(ids);
    }

    /**
     * 修改
     */
    @Override
    public void updateById(Bill bill) {
        billMapper.updateById(bill);
    }

    /**
     * 分页查询
     */
    @Override
    public PageInfo<Bill> selectPage(Bill bill, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Bill> billList = billMapper.selectAll(bill);
        log.info("分页查询结果：{}", PageInfo.of(billList));
        return PageInfo.of(billList);
    }
}
