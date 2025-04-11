import axios from 'axios'
import { ElMessage } from 'element-plus'
import { API_BASE_URL } from '@/api/config'

// 创建axios实例
const request = axios.create({
  baseURL: API_BASE_URL,  // 使用基础URL
  timeout: 15000,  // 设置合理的超时时间
  headers: {
    'Content-Type': 'application/json'
  }
})

// 请求拦截器
request.interceptors.request.use(
  config => {
    // 打印完整的请求URL和数据
    console.log('发送请求:', {
      url: API_BASE_URL + config.url,
      method: config.method,
      data: config.data
    })
    return config
  },
  error => {
    console.error('请求配置错误:', error)
    return Promise.reject(error)
  }
)

// 响应拦截器
request.interceptors.response.use(
  response => {
    console.log('收到响应:', response.data)
    return response.data
  },
  error => {
    console.error('请求错误:', error.response || error)
    return Promise.reject(error)
  }
)

export default request