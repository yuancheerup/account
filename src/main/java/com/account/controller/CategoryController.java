package com.account.controller;

import com.account.pojo.Category;
import com.account.pojo.Result;
import com.account.service.CategoryService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Category category) {
        log.info("查询所有的categorys");
        List<Category> categoryList = categoryService.selectAll(category);
        return Result.success(categoryList);
    }

    /**
     * 添加
     */
    @PostMapping("/add")
    public Result insert(@RequestBody Category category) {
        log.info("添加category！");
        Boolean bl = categoryService.insert(category);
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
        if (!categoryService.selectById(id)) {
            return Result.error("信息不存在，删除失败");
        }
        categoryService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        List<Category> categoryList = categoryService.selectBatch(ids);
        if (categoryList.size() != ids.size()) {
            return Result.error("需要删除的信息有误");
        }
        categoryService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Category category) {
        if (!categoryService.selectById(category.getId())) {
            return Result.error("需要修改的公告不存在");
        }
        categoryService.updateById(category);
        return Result.success();
    }

    /**
     * 分页查询
     * @param category 实现查找功能，为null
     */
    @GetMapping("/selectPage")
    public Result selectPage(Category category,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {

        PageInfo<Category> page = categoryService.selectPage(category, pageNum, pageSize);
        return Result.success(page);
    }
}
