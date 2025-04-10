package com.physical.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class StudentScoreDTO {
    private String studentId;
    private String name;
    private String className;
    private String college;
    private BigDecimal height;
    private BigDecimal weight;
    private BigDecimal run50m;
    private BigDecimal longJump;
    private Integer vitalCapacity;
    private BigDecimal sitAndReach;
    private BigDecimal totalScore;
}