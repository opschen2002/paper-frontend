<template>
  <!-- 模板部分保持不变 -->
  <div class="login-container">
    <el-card class="login-card">
      <template #header>
        <h2>体测成绩管理系统</h2>
      </template>
      
      <el-form 
        :model="loginForm" 
        :rules="rules" 
        ref="loginFormRef"
        @submit.prevent="handleLogin"
      >
        <el-form-item prop="username">
          <el-input 
            v-model="loginForm.username" 
            placeholder="用户名"
            prefix-icon="User"
          />
        </el-form-item>
        
        <el-form-item prop="password">
          <el-input 
            v-model="loginForm.password" 
            type="password" 
            placeholder="密码"
            prefix-icon="Lock"
            show-password
            @keyup.enter="handleLogin"
          />
        </el-form-item>

        <el-form-item prop="role">
          <el-select 
            v-model="loginForm.role" 
            placeholder="请选择角色"
            style="width: 100%"
          >
            <el-option label="管理员" value="admin" />
            <el-option label="教师" value="teacher" />
            <el-option label="学生" value="student" />
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button 
            type="primary" 
            :loading="loading"
            style="width: 100%"
            @click.prevent="handleLogin"
          >
            登录
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { ref, reactive, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { authApi } from '@/api'

export default {
  name: 'LoginView',
  setup() {
    const router = useRouter()
    const loginFormRef = ref(null)
    const loading = ref(false)

    const loginForm = reactive({
      username: '',
      password: '',
      role: ''
    })

    const rules = {
      username: [
        { required: true, message: '请输入用户名', trigger: 'blur' }
      ],
      password: [
        { required: true, message: '请输入密码', trigger: 'blur' }
      ],
      role: [
        { required: true, message: '请选择角色', trigger: 'change' }
      ]
    }

    const testClick = () => {
      console.log('按钮被点击')
      console.log('表单数据:', loginForm)
      console.log('表单引用:', loginFormRef.value)
    }

    const checkNetworkConnection = () => {
      if (!navigator.onLine) {
        ElMessage.error('网络连接已断开，请检查网络设置')
        return false
      }
      return true
    }

const handleLogin = async () => {
  console.log('开始登录处理', new Date().toISOString())
  
  if (!loginFormRef.value) {
    console.error('表单引用不存在')
    return
  }

  try {
    // 表单验证
    const valid = await loginFormRef.value.validate()
    if (!valid) {
      console.log('表单验证失败')
      return
    }

    loading.value = true
    console.log('发送登录请求，数据:', {
      username: loginForm.username,
      password: loginForm.password,
      role: loginForm.role
    })

    // 直接发送请求到完整的URL
    const response = await fetch('http://192.168.203.240:8080/api/auth/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        username: loginForm.username,
        password: loginForm.password,
        role: loginForm.role
      })
    })

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`)
    }

    const res = await response.json()
    console.log('登录响应:', res)

    if (res.code === 200 && res.data) {
      // 存储用户信息
      localStorage.setItem('user', JSON.stringify(res.data))
      localStorage.setItem('role', loginForm.role)
      localStorage.setItem('username', loginForm.username)

      ElMessage.success('登录成功')

      // 根据角色跳转
      const routes = {
        admin: '/admin',
        teacher: '/teacher',
        student: '/student'
      }

      const targetRoute = routes[loginForm.role]
      if (targetRoute) {
        console.log('准备跳转到:', targetRoute)
        await router.push(targetRoute)
      }
    } else {
      throw new Error(res.message || '登录失败')
    }
  } catch (error) {
    console.error('登录错误:', error)
    let errorMessage = '登录失败'
    
    if (error instanceof TypeError && error.message === 'Failed to fetch') {
      errorMessage = '无法连接到服务器，请检查网络连接'
    } else if (error.name === 'AbortError') {
      errorMessage = '请求已取消'
    } else {
      errorMessage = error.message || '登录失败，请稍后重试'
    }
    
    ElMessage.error(errorMessage)
  } finally {
    loading.value = false
  }
}

    onMounted(() => {
      console.log('组件已挂载')
      console.log('表单引用状态:', loginFormRef.value)
      console.log('当前网络状态:', navigator.onLine ? '在线' : '离线')

      window.addEventListener('online', () => {
        console.log('网络已连接')
        ElMessage.success('网络已连接')
      })

      window.addEventListener('offline', () => {
        console.log('网络已断开')
        ElMessage.error('网络已断开，请检查网络设置')
      })
    })

    return {
      loginFormRef,
      loginForm,
      loading,
      rules,
      handleLogin
    }
  }
}
</script>

<style scoped>
.login-container {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #f5f7fa;
}

.login-card {
  width: 400px;
}

.el-form-item {
  margin-bottom: 20px;
}
</style>