package com.physical.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName("basic_physical_scores")
public class BasicPhysicalScore {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String studentId;
    private LocalDate testDate;
    private BigDecimal height;
    private BigDecimal weight;
    private BigDecimal run50m;
    private BigDecimal longJump;
    private Integer vitalCapacity;
    private BigDecimal sitAndReach;
    private LocalDateTime createdAt;
}