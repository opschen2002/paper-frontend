package com.physical.service;

import com.physical.dto.LoginDTO;
import com.physical.entity.User;

public interface UserService {
    /**
     * 用户登录
     * @param loginDTO 登录信息
     * @return 用户信息
     */
    User login(LoginDTO loginDTO);
}