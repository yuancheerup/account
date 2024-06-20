package com.account.service.impl;

import com.account.common.enums.PlanStatusEnum;
import com.account.mapper.PlanMapper;
import com.account.pojo.Plan;
import com.account.service.PlanDetailService;
import com.account.service.PlanService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class PlanServiceImpl implements PlanService {
    @Autowired
    private PlanMapper planMapper;

    @Autowired
    private PlanDetailService planDetailService;

    /**
     * 查询所有
     */
    @Override
    public List<Plan> selectAll(Plan plan) {
        List<Plan> planList = planMapper.selectAll(plan);
        for (Plan p : planList) {
            this.setPlan(p);
        }
        return planList;
    }

    /**
     * 添加
     */
    @Override
    public Boolean insert(Plan plan) {
        // 判断plan是否为null
        if (plan != null) {
            // 设置创建时间
            plan.setCreateTime(LocalDateTime.now());
            planMapper.insert(plan);
            return true;
        }
        return false;
    }

    /**
     * 根据id查询
     */
    @Override
    public Plan selectById(Integer id) {
        Plan plan = planMapper.selectById(id);
        this.setPlan(plan);
        return plan;
    }

    /**
     * 根据id删除
     */
    @Override
    public void deleteById(Integer id) {
        planMapper.deleteById(id);
    }

    /**
     * 批量查询
     */
    @Override
    public List<Plan> selectBatch(List<Integer> ids) {
        return planMapper.selectBatch(ids);
    }

    /**
     * 批量删除
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        planMapper.deleteBatch(ids);
    }

    /**
     * 修改
     */
    @Override
    public void updateById(Plan plan) {
        planMapper.updateById(plan);
    }

    /**
     * 分页查询
     */
    @Override
    public PageInfo<Plan> selectPage(Plan plan, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Plan> planList = planMapper.selectAll(plan);
        PageInfo<Plan> pageInfo = PageInfo.of(planList);
        log.info("分页查询结果：{}", pageInfo);

        for (Plan p : pageInfo.getList()) {
            this.setPlan(p);
        }

        return pageInfo;
    }

    /**
     * 获取已经存的钱的数量，并判断plan的状态
     * @param plan
     */
    public void setPlan(Plan plan) {
        BigDecimal sum = planDetailService.getPlanDetailTotal(plan.getId());

        // 存储的总额超过了计划金额
        if (sum.compareTo(plan.getMoney()) >= 0) {
            plan.setPercent(100);
        } else {
            int percent = sum.divide(plan.getMoney(), 2, RoundingMode.HALF_UP)
                             .multiply(BigDecimal.valueOf(100))
                             .intValue();
            plan.setPercent(percent);
        }

        LocalDate today = LocalDate.now();
        // 设置存钱的状态
        //判断计划的开始日期是否在今天之后
        if (plan.getStart().isAfter(today)) {
            plan.setStatus(PlanStatusEnum.NO_START.getValue());
        } else if ((plan.getStart().isBefore(today) || plan.getStart().isEqual(today)) &&
                (plan.getEnd().isAfter(today) || plan.getEnd().isEqual(today))) {
            // 判断计划的开始日期是否在今天之前或等于今天，并且计划的结束日期是否在今天之后或等于今天
            plan.setStatus(PlanStatusEnum.IS_PROCESS.getValue());
        } else if (plan.getEnd().isBefore(today)) {
            // 判断计划的结束日期是否在今天之前
            plan.setStatus(PlanStatusEnum.END.getValue());
        }

        // 判断存款金额是否达到
        if (sum.compareTo(plan.getMoney()) >= 0) {
            plan.setStatus(PlanStatusEnum.DO_END.getValue());
        }
    }
}
