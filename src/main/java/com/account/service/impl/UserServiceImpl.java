package com.account.service.impl;

import com.account.mapper.UserMapper;
import com.account.pojo.User;
import com.account.service.UserService;
import com.account.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 登录
     */
    @Override
    public User login(User user) {
        if (user.getUsername() == null) {
            return null;
        }
        User u = userMapper.selectUserByUsername(user.getUsername());
        if (u != null && u.getPassword().equals(user.getPassword())) {
            return u;
        }
        return null;
    }

    /**
     * 注册
     */
    @Override
    public void register(User user) {
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    /**
     * 查询所有
     */
    @Override
    public List<User> selectAll(User user) {
        return userMapper.selectAll(user);
    }

    /**
     * 添加
     */
    @Override
    public Boolean insert(User user) {
        log.info("user: {}", user != null);
        // 判断notice是否为null
        if (user != null) {
            // 获取token
            String[] strings = TokenUtils.decodeToken();
            log.info("strings: {}", Arrays.toString(strings));
            // 管理员才能操作
            if (strings[1].equals("ADMIN")) {
                user.setCreateTime(LocalDateTime.now());
                user.setUpdateTime(LocalDateTime.now());
                userMapper.insert(user);
                return true;
            }
        }
        return false;
    }

    /**
     * 根据用户名查询
     */
    @Override
    public User selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    /**
     * 根据id查询
     */
    @Override
    public boolean selectById(Integer id) {
        User user = userMapper.selectById(id);
        return user != null;
    }

    /**
     * 根据id删除
     */
    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    /**
     * 批量查询
     */
    @Override
    public List<User> selectBatch(List<Integer> ids) {
        if (ids.isEmpty()) {
            return null;
        }
        return userMapper.selectBatch(ids);
    }

    /**
     * 批量删除
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        userMapper.deleteBatch(ids);
    }

    /**
     * 修改
     */
    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    /**
     * 分页查询
     */
    @Override
    public PageInfo<User> selectPage(User user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.selectAll(user);
        return PageInfo.of(userList);
    }

    /**
     * 修改密码
     */
    @Override
    public Boolean updatePassword(Integer id, String password, String newPassword) {
        User userById = userMapper.selectById(id);
        if (userById.getPassword().equals(password)) {
            User user = new User();
            user.setId(id);
            user.setPassword(newPassword);
            userMapper.update(user);
            return true;
        }
        return false;
    }
}
