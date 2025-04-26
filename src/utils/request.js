import axios from "axios";
import { ElNotification } from "element-plus";

const request = axios.create({
  // 此处的 '/api' 和 vite.config.js 的配置相关联
  baseURL: "/api",
  timeout: 50000,
  requestOptions: {
    withToken: true,
  },
});

// 数据请求拦截
request.interceptors.request.use(
  // 1. 返回config对象
  // 2. 可以设置携带 token 信息
  (config) => {
    const token = window.localStorage.getItem("access_token");
    token &&
      config?.requestOptions?.withToken &&
      (config.headers.token = token);
    return config;
  },
  (err) => {
    return Promise.reject(err);
  }
);

// 返回响应数据拦截
request.interceptors.response.use(
  (response) => {
    console.log("request.js打印返回信息", response);
    // 简化返回数据
    if (response.data.code === 200) {
      return Promise.resolve(response.data);
    } else {
      ElNotification({
        title: "提示",
        message: response.data.message,
        type: "error",
      });
      return Promise.reject(response.data);
    }
  },
  // 错误执行
  (error) => {
    console.log("错误信息", error);
    if (error.response.status) {
      switch (error.response.status) {
        case 404:
          console.log("请求路径找不到！");
          break;
        case 500:
          console.log("服务器内部报错！");
          break;
        // 还可以自己添加其他状态码
        default:
          break;
      }
    }
    return Promise.reject(new Error(error.message));
  }
);

// 暴露对象
export default request;