package com.account.controller;

import com.account.pojo.PlanDetail;
import com.account.pojo.Result;
import com.account.service.PlanDetailService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/planDetail")
public class PlanDetailController {
    @Autowired
    private PlanDetailService planDetailService;

    /**
     * 根据id查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        log.info("查询计划的id为{}", id);
        if (id == null) {
            return Result.error("查询失败");
        }
        PlanDetail planDetail = planDetailService.selectById(id);
        if (planDetail == null) {
            return Result.error("信息不存在");
        }
        return Result.success(planDetail);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(PlanDetail planDetail) {
        log.info("查询所有的planDetails");
        if (planDetail == null) {
            return Result.error("查询失败");
        }
        List<PlanDetail> planDetailList = planDetailService.selectAll(planDetail);
        return Result.success(planDetailList);
    }

    /**
     * 添加
     */
    @PostMapping("/add")
    public Result insert(@RequestBody PlanDetail planDetail) {
        log.info("添加planDetail！");
        Boolean bl = planDetailService.insert(planDetail);
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
        if (planDetailService.selectById(id) == null) {
            return Result.error("信息不存在，删除失败");
        }
        planDetailService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        List<PlanDetail> planDetailList = planDetailService.selectBatch(ids);
        if (planDetailList.size() != ids.size()) {
            return Result.error("需要删除的信息有误");
        }
        planDetailService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody PlanDetail planDetail) {
        if (planDetailService.selectById(planDetail.getId()) == null) {
            return Result.error("需要修改的公告不存在");
        }
        planDetailService.updateById(planDetail);
        return Result.success();
    }

    /**
     * 分页查询
     *
     * @param planDetail 实现查找功能，为null
     */
//    @GetMapping("/selectPage")
//    public Result selectPage(PlanDetail planDetail,
//                             @RequestParam(defaultValue = "1") Integer pageNum,
//                             @RequestParam(defaultValue = "10") Integer pageSize) {
//
//        PageInfo<PlanDetail> page = planDetailService.selectPage(planDetail, pageNum, pageSize);
//        return Result.success(page);
//    }
}
