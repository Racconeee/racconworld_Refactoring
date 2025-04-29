const { configure } = require("quasar/wrappers");
const path = require("path"); // path 모듈을 불러옵니다.

/* eslint-env node */

/*
 * This file runs in a Node context (it's NOT transpiled by Babel), so use only
 * the ES6 features that are supported by your Node version. https://node.green/
 */

module.exports = configure(function (/* ctx */) {
  return {
    // app boot file (/src/boot)
    boot: ["head", "kakaoshare"],

    // Global CSS
    css: ["app.scss"],

    // Extras like icons
    extras: [
      "material-symbols-outlined",
      "roboto-font", // optional, you are not bound to it
      "material-icons", // optional, you are not bound to it
    ],

    // Build configuration
    build: {
      minify: "terser", // Terser 사용
      terserOptions: {
        compress: {
          drop_console: true, // 모든 console.log 제거
          drop_debugger: true, // 모든 debugger 제거
          pure_funcs: [
            "console.log",
            "console.info",
            "console.warn",
            "console.error",
          ],
        },
      },
      target: {
        browser: ["es2019", "edge88", "firefox78", "chrome87", "safari13.1"],
        node: "node20",
      },
      env: {
        VITE_SERVER_API_URL: process.env.VITE_SERVER_API_URL,
        VITE_COUPANG_URL_LINK: process.env.VITE_COUPANG_URL_LINK,
      },
      sassLoaderOptions: {
        sassOptions: {
          quietDeps: true,
        },
      },
      logLevel: "silent", // 경고 메시지 억제
      vueRouterMode: "history", // available values: 'hash', 'history'

      alias: {
        "@": path.resolve(__dirname, "./src"),
      },
      vite: {
        plugins: [
          [
            "vite-plugin-checker",
            {
              eslint: {
                lintCommand: 'eslint "./**/*.{js,mjs,cjs,vue}"',
              },
            },
            { server: false },
          ],
        ],
      },
    },

    // Dev server configuration
    devServer: {
      open: true, // opens browser window automatically
    },

    // Quasar framework configuration
    framework: {
      config: {},
      plugins: [],
    },

    // Animations configuration
    animations: [],

    // SSR configuration
    ssr: {
      pwa: false,
      prodPort: 3000,
      middlewares: ["render"], // keep this as last one
    },

    // PWA configuration
    pwa: {
      workboxMode: "generateSW", // or 'injectManifest'
      injectPwaMetaTags: true,
      swFilename: "sw.js",
      manifestFilename: "manifest.json",
      useCredentialsForManifestTag: false,
    },

    // Capacitor configuration
    capacitor: {
      hideSplashscreen: true,
    },

    // Electron configuration
    electron: {
      inspectPort: 5858,
      bundler: "packager", // 'packager' or 'builder'
      packager: {},
      builder: {
        appId: "raccon_FE",
      },
    },

    // Browser Extension configuration
    bex: {
      contentScripts: ["my-content-script"],
    },
  };
});

