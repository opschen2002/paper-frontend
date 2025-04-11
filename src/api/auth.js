import request from '@/utils/request'
import { API_URLS } from './config'
import { ElMessage } from 'element-plus'

export const authApi = {
  async login(data) {
    try {
      console.log('开始登录请求:', {
        url: API_URLS.LOGIN,
        data: data
      })

      // 直接使用 axios 发送请求，不使用实例
      const response = await request({
        url: API_URLS.LOGIN,
        method: 'post',
        data: data
      })

      console.log('登录响应:', response)
      return response

    } catch (error) {
      console.error('登录请求失败:', error)
      throw error
    }
  }
}