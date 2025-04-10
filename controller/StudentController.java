package com.physical.controller;

import com.physical.common.ResultVO;
import com.physical.dto.StudentScoreDTO;
import com.physical.service.ScoreService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    
    @Resource
    private ScoreService scoreService;
    
    @GetMapping("/scores/{studentId}")
    public ResultVO<List<StudentScoreDTO>> getHistoryScores(@PathVariable String studentId) {
        List<StudentScoreDTO> scores = scoreService.queryStudentHistoryScores(studentId);
        return ResultVO.success(scores);
    }
}