package com.account.service.impl;

import com.account.mapper.AdminMapper;
import com.account.pojo.Admin;
import com.account.pojo.User;
import com.account.service.AdminService;
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
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 查询所有
     */
    @Override
    public List<Admin> selectAll(Admin admin) {
        return adminMapper.selectAll(admin);
    }

    /**
     * 登录
     */
    @Override
    public Admin login(Admin admin) {
        if (admin == null || admin.getUsername() == null) {
            return null;
        }
        // 管理员存在且密码正确
        Admin loginAdmin = adminMapper.selectByUsername(admin.getUsername());
        log.info("查询到的loginAdmin: {}", loginAdmin);
        if (loginAdmin != null && loginAdmin.getPassword().equals(admin.getPassword())) {
            return loginAdmin;
        }
        return null;
    }

    /**
     * 添加
     */
    @Override
    public Boolean insert(Admin admin) {
        log.info("admin: {}", admin != null);
        // 判断admin是否为null
        if (admin != null) {
            // 获取token
            String[] strings = TokenUtils.decodeToken();
            log.info("strings: {}", Arrays.toString(strings));
            // token不为空，并且是管理员
            if (strings != null && strings[1].equals("ADMIN")) {
                admin.setCreateTime(LocalDateTime.now());
                admin.setUpdateTime(LocalDateTime.now());
                adminMapper.insert(admin);
                return true;
            }
        }
        return false;
    }

    /**
     * 根据用户名查询
     */
    @Override
    public Admin selectByUsername(String username) {
        return adminMapper.selectByUsername(username);
    }

    /**
     * 根据id查询
     */
    @Override
    public boolean selectById(Integer id) {
        Admin admin = adminMapper.selectById(id);
        return admin != null;
    }

    /**
     * 根据id删除
     */
    @Override
    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }

    /**
     * 批量查询
     */
    @Override
    public List<Admin> selectBatch(List<Integer> ids) {
        if (ids.isEmpty()) {
            return null;
        }
        return adminMapper.selectBatch(ids);
    }

    /**
     * 批量删除
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        adminMapper.deleteBatch(ids);
    }

    /**
     * 修改
     */
    @Override
    public void update(Admin admin) {
        admin.setUpdateTime(LocalDateTime.now());
        adminMapper.update(admin);
    }

    /**
     * 分页查询
     */
    @Override
    public PageInfo<Admin> selectPage(Admin admin, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> userList = adminMapper.selectAll(admin);
        return PageInfo.of(userList);
    }

    /**
     * 修改密码
     */
    @Override
    public Boolean updatePassword(Integer id, String password, String newPassword) {
        Admin adminById = adminMapper.selectById(id);
        if (adminById.getPassword().equals(password)) {
            Admin admin = new Admin();
            admin.setId(id);
            admin.setPassword(newPassword);
            adminMapper.update(admin);
            return true;
        }
        return false;

    }
}
