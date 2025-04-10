package com.physical.controller;

import com.alibaba.excel.EasyExcel;
import com.physical.common.ResultVO;
import com.physical.dto.excel.StudentScoreExcelDTO;
import com.physical.excel.ScoreDataListener;
import com.physical.service.ScoreService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/api/admin")
public class FileController {
    
    @Resource
    private ScoreService scoreService;
    
    @PostMapping("/upload")
    public ResultVO<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        EasyExcel.read(
                file.getInputStream(),
                StudentScoreExcelDTO.class,
                new ScoreDataListener(scoreService)
        ).sheet().doRead();
        
        return ResultVO.success("文件上传成功");
    }
}