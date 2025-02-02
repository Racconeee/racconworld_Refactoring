import { useAuthStore } from "src/stores/useAuthStore";

const routes = [
  {
    path: "/",
    alias: "/home",
    name: "home",
    component: () => import("layouts/HomeLayouts.vue"),
    children: [
      {
        path: "",
        name: "basichome",
        component: () => import("pages/HomeView.vue"),
      },
      {
        path: "/quiz/ready/:testId",
        name: "quizReady",
        component: () => import("pages/quiz/QuizReady.vue"),
      },
    ],
  },
  {
    path: "/about",
    name: "about",
    component: () => import("layouts/HomeLayouts.vue"),
    children: [
      {
        path: "",
        name: "basicabout",
        component: () => import("pages/AboutView.vue"),
      },
      {
        path: "privacy-policy",
        name: "privacy",
        component: () => import("pages/privacyView.vue"),
      },
      {
        path: "terms-of-service",
        name: "terms",
        component: () => import("pages/termsView.vue"),
      },
    ],
  },
  {
    path: "/quiz",
    component: () => import("layouts/CommonLayouts.vue"),
    children: [
      {
        path: "",
        name: "quizpage",
        component: () => import("pages/quiz/QuizView.vue"),
      },
      {
        path: "result",
        name: "resultpage",
        component: () => import("pages/result/ResultView.vue"),
      },
    ],
  },
  {
    path: "/login",
    name: "loginpage",
    component: () => import("pages/LoginView.vue"),
  },
  {
    path: "/admin",
    alias: "/admin/home",
    name: "admin",
    component: () => import("layouts/AdminLayouts.vue"),
    meta: { requiresAuth: true },
    //엑세스 토큰을 서버에 인증하고 인증 되면 보내기
    //엑세스 토큰 인증 만들기
    beforeEnter: async (to, from, next) => {
      const authStore = useAuthStore();

      if (authStore.isLoginValue) {
        next(); // 토큰이 유효하면 이동 허용
      } else if (authStore.accessToken) {
        await authStore.validateAccessToken(authStore.accessToken);

        if (authStore.isLoginValue) {
          next(); // 토큰이 유효하면 이동 허용
        } else {
          next("/login"); // 토큰이 유효하지 않으면 로그인 페이지로 이동
        }
      } else {
        next("/login"); // 토큰이 없으면 로그인 페이지로 이동
      }
    },
    children: [
      {
        path: "",
        name: "adminhome",
        component: () => import("pages/admin/AdminMain.vue"),
      },
      {
        path: "info",
        name: "testinfo",
        component: () => import("pages/admin/AdminTestInfo.vue"),
      },
      {
        path: "create",
        name: "create",
        component: () => import("pages/admin/AdminCreate.vue"),
      },
      {
        path: "traffic",
        name: "traffic",
        component: () => import("pages/admin/AdminTraffic.vue"),
      },
    ],
  },
  {
    path: "/:catchAll(.*)*",
    component: () => import("pages/ErrorNotFound.vue"),
  },
];

export default routes;
