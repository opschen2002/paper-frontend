<template>
  <el-container class="layout-container">
    <el-header class="layout-header">
      <div class="header-left">体能测试数据管理系统</div>
      <div class="header-right">
        <span>{{ userName }}</span>
        <el-button type="text" @click="handleLogout">退出登录</el-button>
      </div>
    </el-header>
    <el-container>
      <slot></slot>
    </el-container>
  </el-container>
</template>

<script>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { clearUserInfo } from '@/utils/auth'

export default {
  name: 'BaseLayout',
  setup() {
    const router = useRouter()
    const userName = ref(localStorage.getItem('username') || '')

    const handleLogout = () => {
      clearUserInfo()
      router.push('/login')
    }

    return {
      userName,
      handleLogout
    }
  }
}
</script>

<style scoped>
.layout-container {
  height: 100vh;
}

.layout-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #409EFF;
  color: white;
  padding: 0 20px;
}

.header-right {
  display: flex;
  align-items: center;
  gap: 20px;
}
</style>