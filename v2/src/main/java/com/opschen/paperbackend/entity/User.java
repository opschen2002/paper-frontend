package com.opschen.paperbackend.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 账号：学生的账号使用 students 表中的 studentId；教师和管理员录入独立账号
    @Column(unique = true, nullable = false)
    private String account;

    // 存储加密后的密码（至少6位，建议使用 BCrypt 加密）
    @Column(nullable = false)
    private String password;

    // 用户角色：ADMIN, TEACHER, STUDENT
    @Column(nullable = false)
    private String role;

    // 用户姓名
    private String name;

    // 创建时间
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public void setId(Long id) {
        this.id = id;
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
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}