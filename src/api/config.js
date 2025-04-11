export const API_BASE_URL = 'http://192.168.203.240:8080/api'  // 添加 /api
export const API_URLS = {
  // 认证相关
  LOGIN: '/auth/login',  // 去掉重复的 /api
  
  // 管理员相关
  IMPORT_SCORE: '/admin/score/import',
  UPDATE_SCORE: '/admin/score',
  GET_STUDENT_SCORE: '/admin/score',
  
  // 教师相关
  QUERY_STUDENTS: '/teacher/students',
  GET_CLASS_SCORES: '/teacher/scores/class',
  GET_DATE_RANGE_SCORES: '/teacher/scores/date-range',
  
  // 学生相关
  GET_STUDENT_INFO: '/student/info',
  GET_PERSONAL_SCORE: '/student/score'
}
