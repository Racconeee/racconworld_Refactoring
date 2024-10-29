export default ({ app }) => {
  const VITE_KAKAO_JS_KEY = import.meta.env.VITE_KAKAO_JS_KEY;

  const script = document.createElement("script");
  script.src = "https://developers.kakao.com/sdk/js/kakao.min.js";
  script.onload = () => {
    if (!window.Kakao.isInitialized()) {
      window.Kakao.init(VITE_KAKAO_JS_KEY); // 카카오 앱 키를 입력하세요
    }
  };
  document.head.appendChild(script);
};
