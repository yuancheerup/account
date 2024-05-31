package com.account.controller;

import com.account.pojo.Notice;
import com.account.pojo.Result;
import com.account.pojo.User;
import com.account.service.UserService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private final String userRole = "USER";

    /**
     * 登录
     */
//    @PostMapping("/login")
//    public Result login(@RequestBody User user) {
//        log.info("登录用户：{}", user.getUsername());
//
//        User loginUser = userService.login(user);
//
//        // 登录成功，生成JWT令牌，下发令牌
//        if (loginUser != null) {
//            String data = loginUser.getId() + "-" + loginUser.getRole() + "-" + loginUser.getUsername();
//            String token = TokenUtils.createToken(data);
//            log.info("登录成功：{}", loginUser.getUsername());
//            return Result.success(token);
//        }
//
//        log.info("登录失败");
//        return Result.error("用户名或密码错误");
//    }
//
//    /**
//     * 注册
//     */
//    @PostMapping("/register")
//    public Result register(@RequestBody User user) {
//        user.setRole(userRole);
//        log.info("注册用户：{}", user);
//
//        Boolean register = userService.register(user);
//        if (register) {
//            return Result.success();
//        }
//
//        return Result.error("用户名已经存在");
//    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(User user) {
        log.info("查询所有的users");
        List<User> userList = userService.selectAll(user);
        return Result.success(userList);
    }

    /**
     * 添加
     */
    @PostMapping("/add")
    public Result insert(@RequestBody User user) {
        log.info("insert user: {}", user);
        // 判断用户名是否存在
        User userByUsername = userService.selectUserByUsername(user.getUsername());
        if (userByUsername != null) {
            return Result.error("用户名已经存在");
        }
        user.setRole(userRole);
        Boolean bl = userService.insert(user);
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
        log.info("删除的用户id为：{}", id);
        // 如果删除的数据不存在
        if (!userService.selectById(id)) {
            return Result.error("信息不存在，删除失败");
        }
        userService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        log.info("批量删除的用户ids为：{}", ids);
        List<User> userList = userService.selectBatch(ids);
        if (userList.size() != ids.size()) {
            return Result.error("需要删除的信息有误");
        }
        userService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        log.info("修改的用户为：{}", user.toString());
        if (!userService.selectById(user.getId())) {
            return Result.error("需要修改的用户不存在");
        }
        if (userService.selectUserByUsername(user.getUsername()) != null) {
            return Result.error("用户名已经存在");
        }
        userService.update(user);
        return Result.success();
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(User user,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<User> page = userService.selectPage(user, pageNum, pageSize);
        log.info("分页查询结果：{}", page);
        return Result.success(page);

    }
}
