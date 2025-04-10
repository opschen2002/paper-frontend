package com.physical.dto.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class StudentScoreExcelDTO {
    @ExcelProperty("学号")
    private String studentId;
    
    @ExcelProperty("姓名")
    private String name;
    
    @ExcelProperty("性别")
    private String gender;
    
    @ExcelProperty("班级")
    private String className;
    
    @ExcelProperty("学院")
    private String college;
    
    @ExcelProperty("身高")
    private Double height;
    
    @ExcelProperty("体重")
    private Double weight;
    
    @ExcelProperty("50米跑")
    private Double run50m;
    
    @ExcelProperty("立定跳远")
    private Double longJump;
    
    @ExcelProperty("肺活量")
    private Integer vitalCapacity;
    
    @ExcelProperty("体前屈")
    private Double sitAndReach;
    
    @ExcelProperty("1000米跑")
    private Double run1000m;
    
    @ExcelProperty("引体向上")
    private Integer pullUp;
    
    @ExcelProperty("800米跑")
    private Double run800m;
    
    @ExcelProperty("仰卧起坐")
    private Integer sitUp;
}