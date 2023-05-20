import { createRouter, createWebHistory } from 'vue-router'
import {useStore} from "@/stores";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'welcome',
      component: () => import('@/views/WelcomeView.vue'),
      children: [
        {
          path: '',
          name: 'welcome-login',
          component: () => import('@/components/welcome/LoginPage.vue')
        },
        {
          path: 'register',
          name: 'welcome-register',
          component: () => import('@/components/welcome/RegisterPage.vue')
        },
        {
          path: 'forgot',
          name: 'welcome-forgot',
          component: () => import('@/components/welcome/ForgotPage.vue')
        }
      ]
    }, {
      path: '/index',
      name: "index",
      component: () => import('@/views/IndexView.vue'),
      children: [
        {
          path: 'my-upload',
          name: 'index-my-upload',
          component: () => import('@/components/index/MyUploadPage.vue')
        },
        {
          path: 'my-bought',
          name: 'index-my-bought',
          component: () => import('@/components/index/MyBoughtPage.vue')
        },
        {
          path: 'my-sold',
          name: 'index-my-sold',
          component: () => import('@/components/index/MySoldPage.vue')
        }
      ]
    }, {
      path: '/upload',
      name: "upload",
      component: () => import('@/views/UploadView.vue'),
    }, {
      path: '/good',
      name: "good",
      component: () => import('@/views/GoodView.vue'),
      children: [
        {
          path: '',
          name: 'good-info',
          component: () => import('@/components/good/GoodInfoPage.vue')
        },
        {
          path: 'edit',
          name: 'good-edit',
          component: () => import('@/components/good/GoodEditPage.vue')
        }
      ]
    }
  ]
})

router.beforeEach((to, from, next) => {
  const store = useStore()
  if(store.auth.user != null && to.name.startsWith('welcome-')) {
    next('/index')
  } else if(store.auth.user == null && to.fullPath.startsWith('/index')) {
    next('/')
  } else if(to.matched.length === 0){
    next('/index')
  } else {
    next()
  }
})

export default router
