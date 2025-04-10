package com.physical.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName("male_physical_scores")
public class MalePhysicalScore {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String studentId;
    private LocalDate testDate;
    private BigDecimal run1000m;
    private Integer pullUp;
    private BigDecimal totalScore;
    private LocalDateTime createdAt;
}