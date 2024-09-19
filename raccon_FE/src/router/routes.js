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
    ],
  },
  {
    path: "/test/top",
    component: () => import("pages/TestTopView.vue"),
  },
  {
    path: "/about",
    component: () => import("pages/AboutView.vue"),
  },
  {
    path: "/quiz",
    component: () => import("layouts/CommonLayouts.vue"),
    children: [
      {
        path: "",
        name: "quizpage",
        component: () => import("pages/Quiz/QuizView.vue"),
      },
      {
        path: "result",
        name: "resultpage",
        component: () => import("pages/Result/ResultView.vue"),
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
    // beforeEnter: (to, from, next) => {
    //   const token = 1;

    //   if (token == 1) {
    //     console.log(to, "->", from, " 이동");
    //     next(); // 정상적으로 이동
    //   } else {
    //     console.log(to, "->", from, " 이동불가");
    //     next("/login"); // 이동 중단
    //   }
    // },
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
