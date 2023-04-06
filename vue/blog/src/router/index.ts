import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import NProgress from "nprogress";
import "nprogress/nprogress.css";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/:pathMatch(.*)*",
    component: () => import("../views/Error/404View.vue"),
  },
  {
    path: "/",
    component: () => import("../views/home/HomeView.vue"),
  },
  {
    path: "/articles/:id",
    component: () => import("../views/article/ArticleView.vue"),
  },
  {
    path: "/links",
    component: () => import("../views/article/TestView.vue"),
  },
]

const router = createRouter({
  history: createWebHistory(), // history路由模式
  routes,
});

//跳转前
router.beforeEach((to: any, from: any,next: any) => {
  if (to.fullPath == '/setting') {
    router.push("/")
  }
  NProgress.start();
  next();
});
//跳转后
router.afterEach(() => {
  scrollTo(0, 0);
  NProgress.done();
});

export default router
