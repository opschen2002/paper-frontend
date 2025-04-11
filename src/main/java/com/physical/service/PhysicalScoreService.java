package com.physical.service;

import com.physical.dto.StudentScoreDTO;
import java.time.LocalDate;
import java.util.List;

public interface PhysicalScoreService {
    StudentScoreDTO getLatestScore(String studentId);
    List<StudentScoreDTO> getScoresByClass(String className);
    List<StudentScoreDTO> getScoresByDateRange(LocalDate startDate, LocalDate endDate);
    void saveScore(StudentScoreDTO scoreDTO);
}