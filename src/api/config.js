export const API_BASE_URL = 'http://192.168.203.240:8080'  // 移除末尾的 /api

export const API_URLS = {
  // 认证相关
  LOGIN: '/api/auth/login',  // 添加 /api 前缀
  
  // 管理员相关
  IMPORT_SCORE: '/api/admin/score/import',
  UPDATE_SCORE: '/api/admin/score',
  GET_STUDENT_SCORE: '/api/admin/score',
  
  // 教师相关
  QUERY_STUDENTS: '/api/teacher/students',
  GET_CLASS_SCORES: '/api/teacher/scores/class',
  GET_DATE_RANGE_SCORES: '/api/teacher/scores/date-range',
  
  // 学生相关
  GET_STUDENT_INFO: '/api/student/info',
  GET_PERSONAL_SCORE: '/api/student/score'
}
