package com.physical.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("students")
public class Student {
    @TableId
    private String studentId;
    private String name;
    private String gender;
    private String className;
    private String college;
    private LocalDateTime createdAt;
}