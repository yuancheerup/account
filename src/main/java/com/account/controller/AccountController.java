package com.account.controller;

import com.account.pojo.Account;
import com.account.pojo.Result;
import com.account.service.AccountService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Account account) {
        log.info("查询所有的accounts");
        List<Account> accountList = accountService.selectAll(account);
        return Result.success(accountList);
    }


    /**
     * 添加
     */
    @PostMapping("/add")
    public Result insert(@RequestBody Account account) {
        log.info("添加的account为：{}", account);
        Boolean bl = accountService.insert(account);
        if (bl) {
            return Result.success();
        }
        return Result.error("添加失败");
    }

    /**
     * 根据id删除，需要删除user表中对应的用户
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        log.info("删除账户的id为：{}", id);
        // 如果删除的数据不存在
        if (!accountService.selectById(id)) {
            return Result.error("信息不存在，删除失败");
        }
        accountService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        log.info("批量删除账户的ids为：{}", ids);

        List<Account> accountList = accountService.selectBatch(ids);
        if (accountList.size() != ids.size()) {
            return Result.error("需要删除的信息有误");
        }
        accountService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
//    @PutMapping("/update")
//    public Result updateById(@RequestBody Account account) {
//        if (!accountService.selectById(account.getId())) {
//            return Result.error("需要修改的公告不存在");
//        }
//        accountService.updateById(account);
//        return Result.success();
//    }

    /**
     * 分页查询
     * @param account 实现查找功能，为null
     */
    @GetMapping("/selectPage")
    public Result selectPage(Account account,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {

        PageInfo<Account> page = accountService.selectPage(account, pageNum, pageSize);
        return Result.success(page);
    }
}
