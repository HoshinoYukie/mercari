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
          path: '',
          name: "search",
          component: () => import('@/components/index/SearchPage.vue')
        }, {
          path: '/upload',
          name: "uploadGood",
          component: () => import('@/components/index/UploadPage.vue')
        }, {
          path: '/user',
          name: "user",
          component: () => import('@/components/index/UserPage.vue'),
          children: [
            {
              path: 'my-upload',
              name: 'user-my-upload',
              component: () => import('@/components/index/user/MyUploadPage.vue')
            },
            {
              path: 'my-bought',
              name: 'user-my-bought',
              component: () => import('@/components/index/user/MyBoughtPage.vue')
            },
            {
              path: 'my-sold',
              name: 'user-my-sold',
              component: () => import('@/components/index/user/MySoldPage.vue')
            },
            {
              path: 'my-trading',
              name: 'user-my-trading',
              component: () => import('@/components/index/user/MyTradingPage.vue')
            }
          ]
        }
      ]
    }, {
      path: '/test',
      name: "test",
      component: () => import('@/views/TestView.vue'),
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
