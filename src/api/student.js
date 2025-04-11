import request from '@/utils/request'
import { API_URLS } from './config'

export const studentApi = {
  /**
   * 获取学生信息
   * @param {string} studentId - 学号
   */
  getStudentInfo(studentId) {
    return request({
      url: `${API_URLS.GET_STUDENT_INFO}/${studentId}`,
      method: 'get'
    }).then(response => {
      if (response.code === 200 && response.data) {
        return response.data
      }
      throw new Error(response.message || '获取学生信息失败')
    })
  },

  /**
   * 获取个人成绩
   * @param {string} studentId - 学号
   */
  getPersonalScore(studentId) {
    return request({
      url: `${API_URLS.GET_PERSONAL_SCORE}/${studentId}`,
      method: 'get'
    }).then(response => {
      if (response.code === 200 && response.data) {
        return response.data
      }
      throw new Error(response.message || '获取成绩失败')
    })
  }
}
