package com.physical.controller;

import com.physical.dto.StudentScoreDTO;
import com.physical.entity.CommonResult;
import com.physical.service.PhysicalScoreService;
import com.physical.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private PhysicalScoreService physicalScoreService;
    
    @Autowired
    private StudentService studentService;

    @PostMapping("/score/import")
    public CommonResult<?> importScore(@RequestParam("file") MultipartFile file) {
        try {
            // TODO: 实现Excel导入逻辑
            return CommonResult.success(null);
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
    }

    @GetMapping("/score/{studentId}")
    public CommonResult<StudentScoreDTO> getStudentScore(@PathVariable String studentId) {
        try {
            StudentScoreDTO score = physicalScoreService.getLatestScore(studentId);
            return CommonResult.success(score);
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
    }

    @PutMapping("/score")
    public CommonResult<?> updateScore(@RequestBody StudentScoreDTO scoreDTO) {
        try {
            physicalScoreService.saveScore(scoreDTO);
            return CommonResult.success(null);
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
    }
}