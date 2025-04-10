package com.physical.dto;

import lombok.Data;

@Data
public class PageDTO {
    private Integer current = 1;
    private Integer size = 10;
    private Long total;
}