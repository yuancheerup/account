package com.account.controller;

import com.account.pojo.Notice;
import com.account.pojo.Result;
import com.account.service.NoticeService;
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
    public Result selectAll() {
        log.info("查询所有的notices");
       List<Notice> noticeList = noticeService.selectAll();
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
}
