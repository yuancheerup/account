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
@RequestMapping("/bill")
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

    /**
     * 计算好各账单类型的占比和金额
     */
    @GetMapping("/count")
    public Result getListCount(@RequestParam String type) {
        log.info("计算的类型为{}", type);
        List<Bill> listCount = billService.count(type);
        log.info("计算得到的数据为{}", listCount);
        return Result.success(listCount);
    }

    /**
     * 根据账本id查询
     */
    @GetMapping("/billByBookId")
    public Result selectByBookId(@RequestParam Integer bookId) {
        log.info("查询的账本账单的id为{}", bookId);
        if (bookId == null) {
            return Result.error("查询失败");
        }
        List<Bill> billList = billService.selectByBookId(bookId);
        return Result.success(billList);
    }
}
