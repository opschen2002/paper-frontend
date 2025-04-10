package com.physical.controller;

import com.physical.common.ResultVO;
import com.physical.dto.ScoreUpdateDTO;
import com.physical.dto.StudentScoreDTO;
import com.physical.service.ScoreService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/admin/score")
public class AdminScoreController {
    
    @Resource
    private ScoreService scoreService;
    
    @GetMapping("/{studentId}")
    public ResultVO<StudentScoreDTO> getStudentScore(@PathVariable String studentId) {
        StudentScoreDTO score = scoreService.getStudentScore(studentId);
        return ResultVO.success(score);
    }
    
    @PutMapping
    public ResultVO<String> updateScore(@RequestBody ScoreUpdateDTO scoreUpdateDTO) {
        scoreService.updateScore(scoreUpdateDTO);
        return ResultVO.success("更新成功");
    }
    
    @DeleteMapping("/{studentId}")
    public ResultVO<String> deleteScore(@PathVariable String studentId) {
        scoreService.deleteScore(studentId);
        return ResultVO.success("删除成功");
    }
}