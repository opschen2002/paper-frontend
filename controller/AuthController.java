package com.physical.controller;

import com.physical.common.ResultVO;
import com.physical.dto.LoginDTO;
import com.physical.entity.User;
import com.physical.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Resource
    private UserService userService;
    
    @PostMapping("/login")
    public ResultVO<User> login(@RequestBody LoginDTO loginDTO, HttpSession session) {
        // 调用登录服务
        User user = userService.login(loginDTO);
        
        // 将用户信息存入session
        session.setAttribute("user", user);
        
        // 返回用户信息
        return ResultVO.success(user);
    }
    
    @PostMapping("/logout")
    public ResultVO<String> logout(HttpSession session) {
        // 清除session
        session.invalidate();
        return ResultVO.success("退出成功");
    }
    
    @GetMapping("/current")
    public ResultVO<User> getCurrentUser(HttpSession session) {
        // 获取当前登录用户
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResultVO.error("未登录");
        }
        return ResultVO.success(user);
    }
}