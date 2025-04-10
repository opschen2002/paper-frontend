package com.physical.common;

import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 如果是登录请求，放行
        if (request.getRequestURI().equals("/api/auth/login")) {
            return true;
        }
        
        // 验证session中是否有用户信息
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            response.setStatus(401);
            return false;
        }
        
        return true;
    }
}