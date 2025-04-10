package com.physical.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.physical.common.ResultVO;
import com.physical.dto.ScoreQueryDTO;
import com.physical.dto.StudentScoreDTO;
import com.physical.service.ScoreService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    
    @Resource
    private ScoreService scoreService;
    
    @GetMapping("/scores")
    public ResultVO<Page<StudentScoreDTO>> queryScores(ScoreQueryDTO queryDTO) {
        Page<StudentScoreDTO> page = scoreService.queryStudentScores(queryDTO);
        return ResultVO.success(page);
    }
}