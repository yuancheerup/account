package com.account.controller;

import com.account.pojo.Result;
import com.account.pojo.User;
import com.account.service.AdminService;
import com.account.service.UserService;
import com.account.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class WebController {
    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    private final String userRole = "USER";

    // 用户登录
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        log.info("登录用户：{}", user.getUsername());

        User loginUser = userService.login(user);

        // 登录成功，生成JWT令牌，下发令牌
        if (loginUser != null) {
            String data = loginUser.getId() + "-" + loginUser.getRole() + "-" + loginUser.getUsername();
            String token = TokenUtils.createToken(data);
            log.info("登录成功：{}", loginUser.getUsername());

            Map<String, Object> map = new HashMap<>();
            map.put("id", loginUser.getId());
            map.put("username", loginUser.getUsername());
            map.put("name", loginUser.getName());
            map.put("avatar", loginUser.getAvatar());
            map.put("role", loginUser.getRole());
            map.put("phone", loginUser.getPhone());
            map.put("email", loginUser.getEmail());
            map.put("token", token);

            return Result.success(map);
        }

        log.info("登录失败");
        return Result.error("用户名或密码错误");
    }

    // 用户注册
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        user.setRole(userRole);
        log.info("注册用户：{}", user);
        // 查询用户是否存在
        User userByUsername = userService.selectUserByUsername(user.getUsername());
        if (userByUsername != null) {
            return Result.error("用户名已经存在");
        }

        userService.register(user);
        return Result.success();
    }

    // 修改密码
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Map<String, Object> payload){
        Integer id = (Integer) payload.get("id");
        String password = (String) payload.get("password");
        String newPassword = (String) payload.get("newPassword");
        String role = (String) payload.get("role");

        log.info("修改密码的信息为id：{}、password：{}、newPassword：{}、role：{}", id, password, newPassword, role);

        Boolean bl;
        if (role.equals("USER")) {
            bl = userService.updatePassword(id, password, newPassword);
        } else{
            bl = adminService.updatePassword(id, password, newPassword);
        }
        if (bl) {
            return Result.success();
        }
        return Result.error("原密码错误");

    }
}
