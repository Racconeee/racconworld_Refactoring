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

      //isLoginValue를 사용해보자 이를 통해서 관리자의 로그인 성공여부를 저장
      //isLoginValue 이 true로 변하는순간은 2가지
      // 1. 로그인을 성공 했을떄
      // 2. 새로고침해도 엑세스토큰은 로컬스토리지에 남아있으니 그것을 토대로 확인하기

      //만약 isLoginValue값이 true이면 정상적으로 이동
      console.log("authStore.isLoginValue", authStore.isLoginValue);
      console.log("routes : -> ACToken", authStore.accessToken);
      console.log("routes : -> ACToken", authStore.getAccessToken);

      if (authStore.isLoginValue) {
        console.log(to, "->", from, " 이동");
        next(); // 토큰이 유효하면 이동 허용
      } else if (authStore.accessToken) {
        console.log("authStore.accessToken : ", authStore.accessToken);

        // validateAccessToken이 비동기 함수라면 await 사용
        await authStore.validateAccessToken(authStore.accessToken);

        if (authStore.isLoginValue) {
          console.log(to, "->", from, " 이동");
          next(); // 토큰이 유효하면 이동 허용
        } else {
          console.log("토큰 유효성 검사 실패");
          next("/login"); // 토큰이 유효하지 않으면 로그인 페이지로 이동
        }
      } else {
        console.log("토큰이 없습니다. 로그인 페이지로 이동");
        next("/login"); // 토큰이 없으면 로그인 페이지로 이동
      }

      //아래 코드로 해봤는데 문제가 생기??는 ?? 로직이 이상해진다.
      // store확인 하고 없으면 토큰으로 store고치고 다시 store로 접근??
      //차라리 토큰을 먼저 확인하자.

      // if (authStore.isLoginValue) {
      //   console.log(to, "->", from, " 이동");
      //   next(); // 정상적으로 이동
      // } else if (authStore.accessToken) {
      //   console.log("authStore.accessToken : ", authStore.accessToken);

      //   authStore.validateAccessToken(authStore.accessToken);
      //   if (authStore.isLoginValue) {
      //     next();
      //   }
      // } else {
      //   next("/login"); // 이동 중단
      // }

      // authStore.validateAccessToken(authStore.isLoginValue);
      // if (token == 1) {
      // } else {
      //   console.log(to, "->", from, " 이동불가");
      // }
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

  // Always leave this as last one,
  // but you can also remove it
  {
    path: "/:catchAll(.*)*",
    component: () => import("pages/ErrorNotFound.vue"),
  },
];

export default routes;
