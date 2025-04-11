import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '../views/login/LoginView.vue'
import AdminLayout from '../views/admin/AdminLayout.vue'
import TeacherLayout from '../views/teacher/TeacherLayout.vue'
import StudentView from '../views/student/StudentView.vue'

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'Login',
    component: LoginView
  },
  {
    path: '/admin',
    component: AdminLayout,
    children: [
      {
        path: 'import',
        component: () => import('../views/admin/ImportView.vue')
      },
      {
        path: 'scores',
        component: () => import('../views/admin/ScoresView.vue')
      }
    ],
    meta: { requiresAuth: true, role: 'admin' }
  },
  {
    path: '/teacher',
    component: TeacherLayout,
    children: [
      {
        path: 'query',
        component: () => import('../views/teacher/QueryView.vue')
      },
      {
        path: 'scores',
        component: () => import('../views/teacher/ScoresView.vue')
      }
    ],
    meta: { requiresAuth: true, role: 'teacher' }
  },
  {
    path: '/student',
    component: StudentView,
    meta: { requiresAuth: true, role: 'student' }
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

// 路由守卫
router.beforeEach((to, from, next) => {
  const role = localStorage.getItem('role')
  const username = localStorage.getItem('username')
  
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!username) {  // 使用 username 检查登录状态
      next('/login')
    } else if (to.meta.role && to.meta.role !== role) {
      next('/login')
    } else {
      next()
    }
  } else {
    if (username && to.path === '/login') {  // 使用 username 检查登录状态
      next(`/${role}`)
    } else {
      next()
    }
  }
})

export default router