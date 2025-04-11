import request from '@/utils/request'
import { API_URLS } from './config'

export const adminApi = {
  /**
   * 导入体测成绩
   * @param {FormData} formData - 包含Excel文件的表单数据
   */
  importScore(formData) {
    return request({
      url: API_URLS.IMPORT_SCORE,
      method: 'post',
      data: formData,
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
  },

  /**
   * 更新学生成绩
   * @param {Object} data - 成绩数据
   */
  updateScore(data) {
    return request({
      url: API_URLS.UPDATE_SCORE,
      method: 'put',
      data
    })
  },

  /**
   * 获取学生成绩
   * @param {string} studentId - 学号
   */
  getStudentScore(studentId) {
    return request({
      url: `${API_URLS.GET_STUDENT_SCORE}/${studentId}`,
      method: 'get'
    })
  }
}