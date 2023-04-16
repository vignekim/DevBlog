import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'HomeView',
    component: () => import('@/views/HomeView.vue')
  },
  {
    path: '/User/Create',
    name: 'CreateView',
    component: () => import(/* webpackChunkName: "user" */ '@/views/user/CreateView.vue')
  },
  {
    path: '/User/Info/:userNo',
    name: 'SelectView',
    component: () => import(/* webpackChunkName: "user" */ '@/views/user/SelectView.vue')
  },
  {
    path: '/User/edit/:userNo',
    name: 'EditView',
    component: () => import(/* webpackChunkName: "user" */ '@/views/user/EditView.vue')
  },
  {
    path: '/Notice/Create',
    name: 'CreateNotice',
    component: () => import(/* webpackChunkName: "notice" */ '@/views/notice/CreateNotice.vue')
  },
  {
    path: '/Notice/Detail/:noticeNo',
    name: 'DetailNotice',
    component: () => import(/* webpackChunkName: "notice" */ '@/views/notice/DetailNotice.vue')
  },
  {
    path: '/Login',
    name: 'LoginView',
    component: () => import('@/views/LoginView.vue')
  },
  {
    path: "/notFound",
    name: "notFound",
    component: () => import('@/views/NotFound.vue')
  },
  {
    path: "/:pathMatch(.*)*",
    redirect: "/notFound"
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
