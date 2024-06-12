package com.account.controller;

import com.account.pojo.Bill;
import com.account.pojo.Result;
import com.account.service.BillService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class BillController {
    
    @Autowired
    private BillService billService;

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Bill bill) {
        log.info("查询所有的bills");
        List<Bill> billList = billService.selectAll(bill);
        return Result.success(billList);
    }

    /**
     * 添加
     */
    @PostMapping("/add")
    public Result insert(@RequestBody Bill bill) {
        log.info("添加bill！");
        Boolean bl = billService.insert(bill);
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
        if (!billService.selectById(id)) {
            return Result.error("信息不存在，删除失败");
        }
        billService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        List<Bill> billList = billService.selectBatch(ids);
        if (billList.size() != ids.size()) {
            return Result.error("需要删除的信息有误");
        }
        billService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Bill bill) {
        if (!billService.selectById(bill.getId())) {
            return Result.error("需要修改的公告不存在");
        }
        billService.updateById(bill);
        return Result.success();
    }

    /**
     * 分页查询
     * @param bill 实现查找功能，为null
     */
    @GetMapping("/selectPage")
    public Result selectPage(Bill bill,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {

        PageInfo<Bill> page = billService.selectPage(bill, pageNum, pageSize);
        return Result.success(page);
    }
}
