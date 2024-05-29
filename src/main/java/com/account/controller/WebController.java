package com.account.controller;

import com.account.pojo.Result;
import com.account.pojo.User;
import com.account.service.UserService;
import com.account.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class WebController {
    @Autowired
    private UserService userService;

    private final String userRole = "USER";

    // 用户登录
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        log.info("登录用户：{}", user.getUsername());

        User loginUser = userService.login(user);

        // 登录成功，生成JWT令牌，下发令牌
        if (loginUser != null) {
            String data = loginUser.getId() + "-" + loginUser.getUsername();
            String token = TokenUtils.createToken(data);
            log.info("登录成功：{}", loginUser.getUsername());
            return Result.success(token);
        }

        log.info("登录失败");
        return Result.error("用户名或密码错误");
    }

    // 用户注册
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        user.setRole(userRole);
        log.info("注册用户：{}", user);

        Boolean register = userService.register(user);
        if (register) {
            return Result.success();
        }

        return Result.error("用户名已经存在");
    }

    // 管理员登录
    @PostMapping("/admin/login")
    public Result adminLogin(String username, String password) {
        return Result.success();
    }

}
