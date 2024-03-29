import Vue from 'vue'
import Router from 'vue-router'
import Home from "../components/Home"
import index from '../components/index'
import store from "../vuex";
import MainPage from "../components/MainPage";
import data_V from "../components/data_V";
import baiduMap from "../components/baiduMap";
import Echarts_station from "../components/Echarts_station";
import Time from "../components/Time";
import Overview from "../components/Overview";
import tuoputu from "../components/overview/tuoputu"
import OverviewIndex from "../components/overview/OverviewIndex";
import tryIndex from "../components/overview/tryIndex"
import Welcome from "../components/Welcome";

Vue.use(Router)

/**
 * 刷新页面时，重新赋值token
 */
if (sessionStorage.getItem('token')) {
  store.commit('login',{
    user:sessionStorage.getItem('user'),
    token:sessionStorage.getItem('token')
  });
}

const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/OverviewIndex',
      name: 'Home',
      meta: {requiresAuth: false},
      component: OverviewIndex,
      // redirect:'MainPage/overview/OverviewIndex'
    },
    {
      path: '/welcome',
      name: 'Welcome',
      meta: {requiresAuth: false},
      component: Welcome,
    },
    {
      path: '/',
      name: 'Home',
      meta: {requiresAuth: false},
      component: MainPage,
      redirect:'Welcome'
    },
    {
      path: '/home',
      name: 'Home',
      meta: {requiresAuth: false},
      component: () => import("../components/Home")
    },
    {
      path: '/index',
      name: 'index',
      meta: {requiresAuth: true},
      component: () => import("../components/index")
    },
    {
      path: '/login',
      name: 'login',
      meta: {requiresAuth: false},
      component: () => import("../components/login")
    },
    {
      path: '/register',
      name: 'register',
      meta: {requiresAuth: false},
      component: () => import("../components/Register")
    },
    {
      path: '/mainPage',
      name: 'mainPage',
      component: MainPage,
      children: [
        {
          path: 'data_V',
          name: 'data_V',
          component: data_V
        },
        {
          path: 'overviewIndex',
          name: 'OverviewIndex',
          component: OverviewIndex
        },
        {
          path: 'echarts',
          name: 'echarts',
          component: Echarts_station
        },
        {
          path: 'overview',
          name: 'Overview',
          component: Overview
        },
      ]
    },
  ],
})
router.beforeEach((to, from, next) => {
  if (to.matched.some((r) => r.meta.requiresAuth)) {
    if (store.state.token) {
      next();
    } else {
      next({
        path: '/login',
        query: {redirect: to.fullPath}
      });
    }
  } else {
    next();
  }
})

export default router
