package com.opschen.paperbackend.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "student_test_mapping")
public class StudentTestMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // 学生学号，与 students 表中 studentId 关联
    @Column(name = "student_id", nullable = false)
    private String studentId;
    
    // 成绩记录ID，与 test_scores 表中 id 关联
    @Column(name = "score_id", nullable = false)
    private Long scoreId;
    
    @Column(name = "create_time")
    private LocalDateTime createTime = LocalDateTime.now();

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public Long getScoreId() {
        return scoreId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setScoreId(Long scoreId) {
        this.scoreId = scoreId;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}