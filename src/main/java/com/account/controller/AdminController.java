package com.account.controller;

import com.account.pojo.Admin;
import com.account.pojo.Result;
import com.account.pojo.User;
import com.account.service.AdminService;
import com.account.utils.TokenUtils;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    private final String adminRole = "ADMIN";

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody Admin admin) {
        log.info("管理员：{}正在登录", admin.toString());
        Admin loginAdmin = adminService.login(admin);

        log.info("管理员：{}正在登录", loginAdmin);

        // 登录成功，生成JWT令牌，下发令牌
        if (loginAdmin != null) {
            String data = loginAdmin.getId() + "-" + loginAdmin.getRole() + "-" + loginAdmin.getUsername();
            String token = TokenUtils.createToken(data);

            Map<String, Object> map = new HashMap<>();
            map.put("id", loginAdmin.getId());
            map.put("username", loginAdmin.getUsername());
            map.put("name", loginAdmin.getName());
            map.put("avatar", loginAdmin.getAvatar());
            map.put("role", loginAdmin.getRole());
            map.put("phone", loginAdmin.getPhone());
            map.put("email", loginAdmin.getEmail());
            map.put("token", token);

            log.info("登录成功：{}", loginAdmin.getUsername());
            return Result.success(map);
        }

        log.info("登录失败");
        return Result.error("用户名或密码错误");
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Admin admin) {
        log.info("查询所有的users");
        List<Admin> adminList = adminService.selectAll(admin);
        return Result.success(adminList);
    }

    /**
     * 添加
     */
    @PostMapping("/add")
    public Result insert(@RequestBody Admin admin) {
        log.info("insert admin: {}", admin);
        // 判断用户名是否存在
        Admin adminByUsername = adminService.selectByUsername(admin.getUsername());
        if (adminByUsername != null) {
            return Result.error("用户名已经存在");
        }
        admin.setRole(adminRole);
        Boolean bl = adminService.insert(admin);
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
        log.info("删除的管理员id为：{}", id);
        // 如果删除的数据不存在
        if (!adminService.selectById(id)) {
            return Result.error("信息不存在，删除失败");
        }
        adminService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        log.info("批量删除的管理员的ids为：{}", ids);
        List<Admin> userList = adminService.selectBatch(ids);
        if (userList.size() != ids.size()) {
            return Result.error("需要删除的信息有误");
        }
        adminService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result update(@RequestBody Admin admin) {
        log.info("修改的管理员为：{}", admin.toString());
        if (!adminService.selectById(admin.getId())) {
            return Result.error("需要修改的用户不存在");
        }

        adminService.update(admin);
        return Result.success();
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Admin admin,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("接收的Admin：{}", admin.toString());

        PageInfo<Admin> page = adminService.selectPage(admin, pageNum, pageSize);
        log.info("分页查询结果：{}", page);
        return Result.success(page);

    }
}
