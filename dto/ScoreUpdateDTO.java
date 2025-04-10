package com.physical.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ScoreUpdateDTO {
    private String studentId;
    private BigDecimal height;
    private BigDecimal weight;
    private BigDecimal run50m;
    private BigDecimal longJump;
    private Integer vitalCapacity;
    private BigDecimal sitAndReach;
    // 男生特有项目
    private BigDecimal run1000m;
    private Integer pullUp;
    // 女生特有项目
    private BigDecimal run800m;
    private Integer sitUp;
}