package com.physical.service.impl;

import com.physical.dto.StudentScoreDTO;
import com.physical.entity.*;
import com.physical.repository.*;
import com.physical.service.PhysicalScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PhysicalScoreServiceImpl implements PhysicalScoreService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private BasicPhysicalScoreRepository basicScoreRepository;
    @Autowired
    private MalePhysicalScoreRepository maleScoreRepository;
    @Autowired
    private FemalePhysicalScoreRepository femaleScoreRepository;

    @Override
    public StudentScoreDTO getLatestScore(String studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("学生不存在"));
        
        BasicPhysicalScore basicScore = basicScoreRepository
                .findFirstByStudentIdOrderByTestDateDesc(studentId)
                .orElseThrow(() -> new RuntimeException("未找到体测成绩"));
        
        StudentScoreDTO dto = new StudentScoreDTO();
        // 设置基本信息
        dto.setStudentId(student.getStudentId());
        dto.setName(student.getName());
        dto.setClassName(student.getClassName());
        dto.setCollege(student.getCollege());
        
        // 设置基础体测成绩
        dto.setHeight(basicScore.getHeight());
        dto.setWeight(basicScore.getWeight());
        dto.setRun50m(basicScore.getRun50m());
        dto.setLongJump(basicScore.getLongJump());
        dto.setVitalCapacity(basicScore.getVitalCapacity());
        dto.setSitAndReach(basicScore.getSitAndReach());
        
        // 根据性别设置特有项目成绩
        if (student.getGender() == Student.Gender.male) {
            MalePhysicalScore maleScore = maleScoreRepository
                    .findFirstByStudentIdOrderByTestDateDesc(studentId)
                    .orElseThrow(() -> new RuntimeException("未找到男生体测成绩"));
            dto.setRun1000m(maleScore.getRun1000m());
            dto.setPullUp(maleScore.getPullUp());
            dto.setTotalScore(maleScore.getTotalScore());
        } else {
            FemalePhysicalScore femaleScore = femaleScoreRepository
                    .findFirstByStudentIdOrderByTestDateDesc(studentId)
                    .orElseThrow(() -> new RuntimeException("未找到女生体测成绩"));
            dto.setRun800m(femaleScore.getRun800m());
            dto.setSitUp(femaleScore.getSitUp());
            dto.setTotalScore(femaleScore.getTotalScore());
        }
        
        return dto;
    }

    @Override
    public List<StudentScoreDTO> getScoresByClass(String className) {
        List<Student> students = studentRepository.findByClassName(className);
        List<StudentScoreDTO> scores = new ArrayList<>();
        for (Student student : students) {
            try {
                scores.add(getLatestScore(student.getStudentId()));
            } catch (RuntimeException e) {
                // 如果某个学生没有成绩，继续处理下一个学生
                continue;
            }
        }
        return scores;
    }

    @Override
    @Transactional
    public void saveScore(StudentScoreDTO scoreDTO) {
        // 保存基础体测成绩
        BasicPhysicalScore basicScore = new BasicPhysicalScore();
        basicScore.setStudentId(scoreDTO.getStudentId());
        basicScore.setTestDate(scoreDTO.getTestDate());
        basicScore.setHeight(scoreDTO.getHeight());
        basicScore.setWeight(scoreDTO.getWeight());
        basicScore.setRun50m(scoreDTO.getRun50m());
        basicScore.setLongJump(scoreDTO.getLongJump());
        basicScore.setVitalCapacity(scoreDTO.getVitalCapacity());
        basicScore.setSitAndReach(scoreDTO.getSitAndReach());
        basicScoreRepository.save(basicScore);

        // 根据学生性别保存特有项目成绩
        Student student = studentRepository.findById(scoreDTO.getStudentId())
                .orElseThrow(() -> new RuntimeException("学生不存在"));

        if (student.getGender() == Student.Gender.male) {
            MalePhysicalScore maleScore = new MalePhysicalScore();
            maleScore.setStudentId(scoreDTO.getStudentId());
            maleScore.setTestDate(scoreDTO.getTestDate());
            maleScore.setRun1000m(scoreDTO.getRun1000m());
            maleScore.setPullUp(scoreDTO.getPullUp());
            maleScore.setTotalScore(scoreDTO.getTotalScore());
            maleScoreRepository.save(maleScore);
        } else {
            FemalePhysicalScore femaleScore = new FemalePhysicalScore();
            femaleScore.setStudentId(scoreDTO.getStudentId());
            femaleScore.setTestDate(scoreDTO.getTestDate());
            femaleScore.setRun800m(scoreDTO.getRun800m());
            femaleScore.setSitUp(scoreDTO.getSitUp());
            femaleScore.setTotalScore(scoreDTO.getTotalScore());
            femaleScoreRepository.save(femaleScore);
        }
    }

    @Override
    public List<StudentScoreDTO> getScoresByDateRange(LocalDate startDate, LocalDate endDate) {
        List<BasicPhysicalScore> basicScores = basicScoreRepository.findByTestDateBetween(startDate, endDate);
        List<StudentScoreDTO> scores = new ArrayList<>();
        for (BasicPhysicalScore basicScore : basicScores) {
            try {
                scores.add(getLatestScore(basicScore.getStudentId()));
            } catch (RuntimeException e) {
                continue;
            }
        }
        return scores;
    }
}