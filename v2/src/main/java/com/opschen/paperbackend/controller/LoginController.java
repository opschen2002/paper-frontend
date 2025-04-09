package com.opschen.paperbackend.controller;

import com.opschen.paperbackend.entity.User;
import com.opschen.paperbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static class LoginRequest {
        private String account;
        private String password;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class LoginResponse {
        private String token;
        private String role;
        private String message;

        public LoginResponse(String token, String role, String message) {
            this.token = token;
            this.role = role;
            this.message = message;
        }

        public String getToken() {
            return token;
        }

        public String getRole() {
            return role;
        }

        public String getMessage() {
            return message;
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String account = loginRequest.getAccount();
        String rawPassword = loginRequest.getPassword();
        User user = userRepository.findByAccount(account);
        if (user == null || !passwordEncoder.matches(rawPassword, user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid account or password.");
        }
        // 使用简单 token 标识，实际可使用 JWT 等机制实现
        String token = "dummy-token-for-" + account;
        LoginResponse response = new LoginResponse(token, user.getRole(), "Login successful");
        return ResponseEntity.ok(response);
    }
}