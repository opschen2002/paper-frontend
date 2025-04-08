package com.opschen.paperbackend.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 学号，与登录时的账号保持一致
    @Column(name = "student_id", unique = true, nullable = false)
    private String studentId;
    
    @Column(nullable = false)
    private String name;
    
    // 可取值 "男" 或 "女"
    private String gender;
    
    @Column(name = "class_name")
    private String className;
    
    @Column(name = "create_time")
    private LocalDateTime createTime = LocalDateTime.now();

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    } 

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}