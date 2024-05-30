package com.account.controller;

import com.account.pojo.Notice;
import com.account.pojo.Result;
import com.account.service.NoticeService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Notice notice) {
        log.info("查询所有的notices");
       List<Notice> noticeList = noticeService.selectAll(notice);
       return Result.success(noticeList);
    }

    /**
     * 添加
     */
    @PostMapping("/add")
    public Result insert(@RequestBody Notice notice) {
        log.info("添加notice！");
        Boolean bl = noticeService.insert(notice);
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
        if (!noticeService.selectById(id)) {
            return Result.error("信息不存在，删除失败");
        }
        noticeService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        List<Notice> noticeList = noticeService.selectBatch(ids);
        if (noticeList.size() != ids.size()) {
            return Result.error("需要删除的信息有误");
        }
        noticeService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Notice notice) {
        if (!noticeService.selectById(notice.getId())) {
            return Result.error("需要修改的公告不存在");
        }
        noticeService.updateById(notice);
        return Result.success();
    }

    /**
     * 分页查询
     * @param notice 实现查找功能，为null
     */
    @GetMapping("/selectPage")
    public Result selectPage(Notice notice,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {

        PageInfo<Notice> page = noticeService.selectPage(notice, pageNum, pageSize);
        return Result.success(page);
    }

}
