package com.account.controller;

import com.account.pojo.Diary;
import com.account.pojo.Result;
import com.account.service.DiaryService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/diary")
public class DiaryController {
    
    @Autowired
    private DiaryService diaryService;

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Diary diary) {
        log.info("查询所有的diarys");
        List<Diary> diaryList = diaryService.selectAll(diary);
        return Result.success(diaryList);
    }

    /**
     * 添加
     */
    @PostMapping("/add")
    public Result insert(@RequestBody Diary diary) {
        log.info("添加diary！");
        Boolean bl = diaryService.insert(diary);
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
        if (!diaryService.selectById(id)) {
            return Result.error("信息不存在，删除失败");
        }
        diaryService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        List<Diary> diaryList = diaryService.selectBatch(ids);
        if (diaryList.size() != ids.size()) {
            return Result.error("需要删除的信息有误");
        }
        diaryService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Diary diary) {
        log.info("修改的公告为{}", diary);
        if (!diaryService.selectById(diary.getId())) {
            return Result.error("需要修改的公告不存在");
        }
        diaryService.updateById(diary);
        return Result.success();
    }

    /**
     * 分页查询
     * @param diary 实现查找功能，为null
     */
    @GetMapping("/selectPage")
    public Result selectPage(Diary diary,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {

        PageInfo<Diary> page = diaryService.selectPage(diary, pageNum, pageSize);
        return Result.success(page);
    }
    
}
