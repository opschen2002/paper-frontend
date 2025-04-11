package com.physical.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "students")
public class Student {
    @Id
    @Column(name = "student_id", length = 20)
    private String studentId;  // 学号

    @Column(nullable = false, length = 50)
    private String name;      // 姓名

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;    // 性别

    @Column(name = "class_name", nullable = false, length = 50)
    private String className; // 班级

    @Column(nullable = false, length = 50)
    private String college;   // 学院

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public enum Gender {
        male, female
    }
}