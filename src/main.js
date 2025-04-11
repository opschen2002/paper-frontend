import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
import axios from 'axios'

const app = createApp(App)

// 配置axios
axios.defaults.baseURL = '/api'
app.config.globalProperties.$http = axios

app.use(ElementPlus)
app.use(router)
app.mount('#app')