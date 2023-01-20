import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from '../layout/Layout'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect:"/user",//路由自动跳转
    children:[
      {
        path:'user',
        name:'User',
        component:()=>import("@/views/User")
        //其他模块也可以放在这里
      },
      {
        path:'factory',
        name:'Factory',
        component:()=>import("@/views/Factory")
      },
      {
        path:'product',
        name:'Product',
        component:()=>import("@/views/Product")
      },
      {
        path:'device',
        name:'Device',
        component:()=>import("@/views/Device")
      },
      {
        path:'facorder',
        name:'FacOrder',
        component:()=>import("@/views/FacOrder")
      },
      {
        path:'devicetype',
        name:'DeviceType',
        component:()=>import("@/views/DeviceType")
      },
      {
        path:'producttype',
        name:'ProductType',
        component:()=>import("@/views/ProductType")
      }
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: () =>import("@/views/Login")
  },
  {
    path: '/register',
    name: 'Register',
    component: () =>import("@/views/Register")
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
