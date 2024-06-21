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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    /**
     * 计算好各账单类型的占比和金额
     */
    public List<Bill> count(String type) {
        // 获取当前用户信息
        String[] currentUserData = TokenUtils.decodeToken();
        int userId = Integer.parseInt(currentUserData[0]);

        // 获取用户指定类型（支出或收入）的所有账单
        Bill bill = new Bill();
        bill.setUserId(userId);
        bill.setType(type);
        List<Bill> billList = billMapper.selectAll(bill);
        log.info("billList::::::{}", billList);
        // 计算总金额
        BigDecimal totalAmount = billList.stream()
                .map(Bill::getMoney)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        log.info("totalAmount::::::{}", totalAmount);
        // 获取所有分类
        List<String> categoryList = billMapper.selectCategoryByType(type);
        log.info("categoryList::::::{}", categoryList);
        // 计算每个分类的金额和百分比
        List<Bill> list = new ArrayList<>();
        for (String category : categoryList) {
            Bill b = new Bill();
            b.setCategory(category);
            // 统计出当前这个分类的所有的金额汇总
            BigDecimal categorySum = billList.stream()
                    .filter(bi -> bi.getCategory().equals(category))
                    .map(Bill::getMoney).reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO);
            // 返回当前分类的总金额
            b.setMoney(categorySum);
            // 得到账单的百分比
            b.setPercent(categorySum.divide(totalAmount, 2, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100))
                    .intValue());
            list.add(b);
        }
        return list;
    }

    @Override
    public List<Bill> selectByBookId(Integer bookId) {
        return billMapper.selectByBookId(bookId);
    }
}
