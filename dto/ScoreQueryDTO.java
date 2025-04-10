package com.physical.dto;

import lombok.Data;

@Data
public class ScoreQueryDTO {
    private String college;
    private String className;
    private String studentId;
    private Integer page = 1;
    private Integer size = 10;
}