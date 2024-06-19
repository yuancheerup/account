package com.account.controller;

import com.account.pojo.Plan;
import com.account.pojo.Result;
import com.account.service.PlanService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    private PlanService planService;

    /**
     * 根据id查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        log.info("查询计划的id为{}", id);
        if (id == null) {
            return Result.error("查询失败");
        }
        Plan plan = planService.selectById(id);
        if (plan == null) {
            return Result.error("信息不存在");
        }
        return Result.success(plan);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Plan plan) {
        log.info("查询所有的plans");
        List<Plan> planList = planService.selectAll(plan);
        return Result.success(planList);
    }

    /**
     * 添加
     */
    @PostMapping("/add")
    public Result insert(@RequestBody Plan plan) {
        log.info("添加plan！");
        Boolean bl = planService.insert(plan);
        if (bl) {
            return Result.success();
        }
        return Result.error("添加失败");
    }

    /**
     * 根据id删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        // 如果删除的数据不存在
        if (planService.selectById(id) == null) {
            return Result.error("信息不存在，删除失败");
        }
        planService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        List<Plan> planList = planService.selectBatch(ids);
        if (planList.size() != ids.size()) {
            return Result.error("需要删除的信息有误");
        }
        planService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Plan plan) {
        if (planService.selectById(plan.getId()) == null) {
            return Result.error("需要修改的公告不存在");
        }
        planService.updateById(plan);
        return Result.success();
    }

    /**
     * 分页查询
     *
     * @param plan 实现查找功能，为null
     */
    @GetMapping("/selectPage")
    public Result selectPage(Plan plan,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {

        PageInfo<Plan> page = planService.selectPage(plan, pageNum, pageSize);
        return Result.success(page);
    }
}
