import axios from 'axios';

// 根据实际后端地址配置 baseURL
const api = axios.create({
  baseURL: "http://localhost:8080/api", // 注意修改为你的后端地址和端口
});

// 拦截请求，附加 token
api.interceptors.request.use(
  config => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  error => Promise.reject(error)
);

export default api;