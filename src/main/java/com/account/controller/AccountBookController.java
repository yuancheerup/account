package com.account.controller;

import com.account.pojo.AccountBook;
import com.account.pojo.Result;
import com.account.service.AccountBookService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/accountBook")
public class AccountBookController {
    @Autowired
    private AccountBookService accountBookService;

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(AccountBook accountBook) {
        log.info("查询所有的accountBooks");
        List<AccountBook> accountBookList = accountBookService.selectAll(accountBook);
        return Result.success(accountBookList);
    }

    /**
     * 添加
     */
    @PostMapping("/add")
    public Result insert(@RequestBody AccountBook accountBook) {
        log.info("添加accountBook！");
        Boolean bl = accountBookService.insert(accountBook);
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
        if (!accountBookService.selectById(id)) {
            return Result.error("信息不存在，删除失败");
        }
        accountBookService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        List<AccountBook> accountBookList = accountBookService.selectBatch(ids);
        if (accountBookList.size() != ids.size()) {
            return Result.error("需要删除的信息有误");
        }
        accountBookService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody AccountBook accountBook) {
        log.info("修改的公告为{}", accountBook);
        if (!accountBookService.selectById(accountBook.getId())) {
            return Result.error("需要修改的公告不存在");
        }
        accountBookService.updateById(accountBook);
        return Result.success();
    }

    /**
     * 分页查询
     * @param accountBook 实现查找功能，为null
     */
    @GetMapping("/selectPage")
    public Result selectPage(AccountBook accountBook,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {

        PageInfo<AccountBook> page = accountBookService.selectPage(accountBook, pageNum, pageSize);
        return Result.success(page);
    }
}
