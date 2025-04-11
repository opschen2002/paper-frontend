import request from '@/utils/request'
import { API_URLS } from './config'

export const teacherApi = {
  /**
   * 查询学生列表
   * @param {Object} params - 查询参数
   * @param {string} [params.className] - 班级名称
   * @param {string} [params.college] - 学院名称
   * @param {string} [params.keyword] - 搜索关键词
   */
  queryStudents(params) {
    return request({
      url: API_URLS.QUERY_STUDENTS,
      method: 'get',
      params
    })
  },

  /**
   * 获取班级成绩
   * @param {string} className - 班级名称
   */
  getClassScores(className) {
    return request({
      url: `${API_URLS.GET_CLASS_SCORES}/${className}`,
      method: 'get'
    })
  },

  /**
   * 按日期范围查询成绩
   * @param {string} startDate - 开始日期
   * @param {string} endDate - 结束日期
   */
  getScoresByDateRange(startDate, endDate) {
    return request({
      url: API_URLS.GET_DATE_RANGE_SCORES,
      method: 'get',
      params: { startDate, endDate }
    })
  }
}