import axios from 'axios'
import { ElMessage } from 'element-plus'
import { API_BASE_URL } from '@/api/config'

// 创建axios实例
const request = axios.create({
  baseURL: API_BASE_URL,
  timeout: 15000,
  headers: {
    'Content-Type': 'application/json'
  }
})

// 请求拦截器
request.interceptors.request.use(
  config => {
    // 直接使用config.url，因为axios会自动和baseURL组合
    console.log('发送请求:', {
      url: config.url,          // 修改这里，不要手动拼接
      baseURL: config.baseURL,  // 添加这行，查看基础URL
      fullPath: config.baseURL + config.url, // 添加这行，查看完整URL
      method: config.method,
      data: config.data,
      headers: config.headers   // 添加这行，查看请求头
    })
    
    // 添加token等认证信息（如果需要的话）
    const token = localStorage.getItem('token')
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`
    }
    
    return config
  },
  error => {
    console.error('请求配置错误:', error)
    ElMessage.error('请求配置错误')
    return Promise.reject(error)
  }
)

// 响应拦截器
request.interceptors.response.use(
  response => {
    console.log('收到响应:', {
      status: response.status,
      data: response.data,
      headers: response.headers
    })
    
    // 自定义错误处理
    if (response.data.code !== 200) {
      ElMessage.error(response.data.message || '请求失败')
      return Promise.reject(new Error(response.data.message || '请求失败'))
    }
    
    return response.data
  },
  error => {
    console.error('请求错误:', {
      config: error.config,
      response: error.response,
      message: error.message
    })
    
    // 错误提示
    if (error.response) {
      // 有响应但状态码不是2xx
      switch(error.response.status) {
        case 404:
          ElMessage.error('请求的资源不存在')
          break
        case 401:
          ElMessage.error('未授权，请重新登录')
          // 可以在这里处理登录过期
          break
        case 403:
          ElMessage.error('没有权限访问该资源')
          break
        default:
          ElMessage.error(`请求失败: ${error.message}`)
      }
    } else if (error.request) {
      // 发出了请求但没有响应
      ElMessage.error('服务器无响应')
    } else {
      // 请求配置出错
      ElMessage.error('请求配置错误')
    }
    
    return Promise.reject(error)
  }
)

export default request

