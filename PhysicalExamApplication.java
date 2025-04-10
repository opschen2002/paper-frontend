package com.physical;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.physical.mapper")
public class PhysicalExamApplication {
    public static void main(String[] args) {
        SpringApplication.run(PhysicalExamApplication.class, args);
    }
}