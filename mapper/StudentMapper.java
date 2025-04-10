@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    // ... 之前的方法省略 ...

    /**
     * 查询学生历史成绩
     */
    List<StudentScoreDTO> getStudentHistoryScores(@Param("studentId") String studentId);
}