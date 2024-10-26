import { route } from "quasar/wrappers";
import {
  createRouter,
  createMemoryHistory,
  createWebHistory,
  createWebHashHistory,
} from "vue-router";
import routes from "./routes";

const VITE_NGINX_IMG_URL = import.meta.env.VITE_NGINX_IMG_URL;

export default route(function (/* { store, ssrContext } */) {
  const createHistory = process.env.SERVER
    ? createMemoryHistory
    : process.env.VUE_ROUTER_MODE === "history"
    ? createWebHistory
    : createWebHashHistory;

  const Router = createRouter({
    scrollBehavior: () => ({ left: 0, top: 0 }),
    routes,

    history: createHistory(process.env.VUE_ROUTER_BASE),
  });

  // 라우터 훅에서 메타 태그를 설정
  // Router.beforeEach((to, from, next) => {
  //   const authStore = useAuthStore();

  //   // getAccessToken 호출로 토큰 확인
  //   const token = authStore.getAccessToken();

  //   // 인증이 필요한 페이지에 접근하는데 토큰이 없을 경우 로그인 페이지로 리다이렉트
  //   if (to.meta.requiresAuth && !token) {
  //     next({ name: "loginpage" }); // 토큰이 없으니 로그인 페이지로 이동
  //   }
  //   // 인증된 사용자라면 원래 가려던 페이지로 이동
  //   else if (to.meta.requiresAuth && token) {
  //     console.log("else if  호출");

  //     next(); // 요청한 페이지로 이동 (예: admin 페이지)
  //   }
  //   // 인증이 필요 없는 페이지는 그냥 이동 (예: 홈, 정보 페이지 등)
  //   else {
  //     console.log("else 호출");

  //     next(); // 별도의 제한 없이 이동
  //   }

  // pinia값에 따라 login , admin 페이지로 리다이렉트하고
  //만약 권한이 필요없는 페이지라면 그냥 이동 시킨다.
  // if (to.meta.requiresAuth && !authStore.isLoginValue) {
  //   console.log("loginpage 이동");
  //   next({ name: "loginpage" });
  // } else if (to.meta.requiresAuth && authStore.isLoginValue) {
  //   console.log("admin 페이지 이동");

  //   next({ name: "admin" });
  // } else {
  //   next(); // 그렇지 않으면 페이지로 이동
  // }
  // });

  return Router;
});
