// 在现有的ScoreServiceImpl类中添加如下方法

@Override
public Page<StudentScoreDTO> queryStudentScores(ScoreQueryDTO queryDTO) {
    Page<StudentScoreDTO> page = new Page<>(queryDTO.getPage(), queryDTO.getSize());
    
    // 使用StudentMapper中的自定义查询方法
    List<StudentScoreDTO> records = studentMapper.getStudentScoreList(
            queryDTO.getCollege(),
            queryDTO.getClassName(),
            queryDTO.getStudentId()
    );
    
    // 手动设置分页数据
    int start = (queryDTO.getPage() - 1) * queryDTO.getSize();
    int end = Math.min(start + queryDTO.getSize(), records.size());
    
    page.setRecords(records.subList(start, end));
    page.setTotal(records.size());
    
    return page;
}

@Override
public List<StudentScoreDTO> queryStudentHistoryScores(String studentId) {
    // 查询该学生的所有历史成绩
    return studentMapper.getStudentHistoryScores(studentId);
}