package com.account.service.impl;

import com.account.mapper.PlanDetailMapper;
import com.account.pojo.PlanDetail;
import com.account.service.PlanDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class PlanDetailServiceImpl implements PlanDetailService {
    @Autowired
    private PlanDetailMapper planDetailMapper;

    /**
     * 查询所有
     */
    @Override
    public List<PlanDetail> selectAll(PlanDetail planDetail) {
        return planDetailMapper.selectAll(planDetail);
    }

    /**
     * 添加
     */
    @Override
    public Boolean insert(PlanDetail planDetail) {
        // 判断planDetail是否为null
        if (planDetail != null) {
            // 获取插入总金额
            BigDecimal planDetailTotal = getPlanDetailTotal(planDetail.getPlanId());
            planDetail.setTotal(planDetailTotal.add(planDetail.getMoney()));
            planDetail.setDate(LocalDateTime.now());
            planDetailMapper.insert(planDetail);
            return true;
        }
        return false;
    }

    /**
     * 根据id查询
     */
    @Override
    public PlanDetail selectById(Integer id) {
        return planDetailMapper.selectById(id);
    }

    /**
     * 根据id, planId删除、修改
     */
    @Override
    public void deleteById(Integer id, Integer planId) {
        // 删除planId的计划的详情时，需要更新这个表
        planDetailMapper.deleteById(id, planId);
    }

    /**
     * 批量查询
     */
    @Override
    public List<PlanDetail> selectBatch(List<Integer> ids) {
        return planDetailMapper.selectBatch(ids);
    }

    /**
     * 批量删除
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        planDetailMapper.deleteBatch(ids);
    }

    /**
     * 修改
     */
    @Override
    public void updateById(PlanDetail planDetail) {
        planDetailMapper.updateById(planDetail);
    }

    /**
     * 分页查询
     */
//    @Override
//    public PageInfo<PlanDetail> selectPage(PlanDetail planDetail, Integer pageNum, Integer pageSize) {
//        PageHelper.startPage(pageNum, pageSize);
//        List<PlanDetail> planDetailList = planDetailMapper.selectAll(planDetail);
//        log.info("分页查询结果：{}", PageInfo.of(planDetailList));
//        return PageInfo.of(planDetailList);
//    }

    /**
     * 查询最新的planId的planDetail，获取已经存储的总金额
     */
    public BigDecimal getPlanDetailTotal(Integer planId) {
        PlanDetail planDetail = new PlanDetail();
        planDetail.setPlanId(planId);

        // 查询planId的planDetail
        List<PlanDetail> planDetails = planDetailMapper.selectAll(planDetail);
        BigDecimal sum = BigDecimal.ZERO;
        if (!planDetails.isEmpty()) {
            // 获取最新的planDetail
            PlanDetail planDetail1 = planDetails.get(0);
            sum = planDetail1.getTotal();
        }
        return sum;
    }
}
