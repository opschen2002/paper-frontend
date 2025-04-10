package com.physical.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.physical.dto.LoginDTO;
import com.physical.entity.User;
import com.physical.mapper.UserMapper;
import com.physical.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    
    @Resource
    private UserMapper userMapper;

    @Override
    public User login(LoginDTO loginDTO) {
        // 构建查询条件
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, loginDTO.getUsername())
               .eq(User::getPassword, loginDTO.getPassword())
               .eq(User::getRole, loginDTO.getRole());
        
        // 查询用户
        User user = userMapper.selectOne(wrapper);
        
        if (user == null) {
            throw new RuntimeException("用户名或密码错误");
        }
        
        return user;
    }
}