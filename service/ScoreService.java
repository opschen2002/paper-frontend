package com.physical.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.physical.dto.ScoreQueryDTO;
import com.physical.dto.StudentScoreDTO;
import java.util.List;

public interface ScoreService {
    // ... 之前的方法省略 ...

    /**
     * 教师查询学生成绩（分页）
     */
    Page<StudentScoreDTO> queryStudentScores(ScoreQueryDTO queryDTO);

    /**
     * 查询单个学生所有成绩
     */
    List<StudentScoreDTO> queryStudentHistoryScores(String studentId);
}