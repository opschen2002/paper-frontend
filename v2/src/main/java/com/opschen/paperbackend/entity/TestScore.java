package com.opschen.paperbackend.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "test_scores")
public class TestScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "score_date")
    private LocalDate scoreDate;
    
    @Column(name = "fifty_meter_run")
    private Double fiftyMeterRun;
    
    @Column(name = "long_jump")
    private Double longJump;
    
    @Column(name = "pull_ups")
    private Integer pullUps;
    
    @Column(name = "sit_ups")
    private Integer sitUps;
    
    @Column(name = "vital_capacity")
    private Double vitalCapacity;
    
    @Column(name = "create_time")
    private LocalDateTime createTime = LocalDateTime.now();
    
    // 关联学生的学号（与 students 表中 studentId 关联）
    @Column(name = "student_id")
    private String studentId;
    
    // Getters and Setters

    public Long getId() {
        return id;
    }

    public LocalDate getScoreDate() {
        return scoreDate;
    }

    public void setScoreDate(LocalDate scoreDate) {
        this.scoreDate = scoreDate;
    }

    public Double getFiftyMeterRun() {
        return fiftyMeterRun;
    }

    public void setFiftyMeterRun(Double fiftyMeterRun) {
        this.fiftyMeterRun = fiftyMeterRun;
    }

    public Double getLongJump() {
        return longJump;
    }

    public void setLongJump(Double longJump) {
        this.longJump = longJump;
    }

    public Integer getPullUps() {
        return pullUps;
    }

    public void setPullUps(Integer pullUps) {
        this.pullUps = pullUps;
    }

    public Integer getSitUps() {
        return sitUps;
    }

    public void setSitUps(Integer sitUps) {
        this.sitUps = sitUps;
    }

    public Double getVitalCapacity() {
        return vitalCapacity;
    }

    public void setVitalCapacity(Double vitalCapacity) {
        this.vitalCapacity = vitalCapacity;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}