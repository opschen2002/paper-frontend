package com.physical.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.physical.dto.excel.StudentScoreExcelDTO;
import com.physical.service.ScoreService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ScoreDataListener extends AnalysisEventListener<StudentScoreExcelDTO> {
    
    private static final int BATCH_COUNT = 100;
    
    private List<StudentScoreExcelDTO> list = new ArrayList<>();
    
    private final ScoreService scoreService;
    
    public ScoreDataListener(ScoreService scoreService) {
        this.scoreService = scoreService;
    }
    
    @Override
    public void invoke(StudentScoreExcelDTO data, AnalysisContext context) {
        list.add(data);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }
    
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        list.clear();
    }
    
    private void saveData() {
        if (!list.isEmpty()) {
            scoreService.batchImportScore(list);
        }
    }
}