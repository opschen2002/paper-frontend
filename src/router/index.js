import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/home'
    },
    {
      path: '/',
      name: 'layout',
      component: () => import('../views/LayoutView.vue'),
      children: [
        {
          path: 'home',
          name: 'home',
          component: HomeView,
          beforeEnter: (to, from, next) => {
            const tokens = localStorage.getItem('tokens')
            const role = tokens?.role
            if (role === 'student') {
              next({ name: 'student' })
            } else {
              next()
            }
          }
        },
        {
          path: 'student',
          name: 'student',
          component: () => import('../views/StudentView.vue'),
        }
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/LoginView.vue'),
      beforeEnter: (to, from, next) => {
        const tokens = localStorage.getItem('tokens')
        if (tokens) {
          next({ name: 'home' })
        } else {
          next()
        }
      }
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue'),
    },
  ],
})

router.beforeEach((to, from, next) => {
  const tokens = localStorage.getItem('tokens')
  if (!tokens && to.name !== 'login') {
    next({ name: 'login' })
  } else {
    next()
  }
})

export default router
