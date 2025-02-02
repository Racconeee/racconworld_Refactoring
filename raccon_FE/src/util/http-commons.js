import axios from "axios";
// import { httpStatusCode } from "./http-status";

// 아래와 같이 VITE_SERVER_API_URL 까지 기입 해줘야한다. + vite에서는 meta를 사용해야 구조할당이 된다.
// .env 파일에서 환경 변수를 설정할떄 모든 변수는 VITE_로 시작해야 Vite가 이 변수를 빌드 시점에 인식해서 사용한다.
// .env 뒤에 폴더 명이 있어도 그냥 import.meta.env를 통해서 접근이 가능하다.

const VITE_SERVER_API_URL = import.meta.env.VITE_SERVER_API_URL;

const authAxios = axios.create({
  baseURL: VITE_SERVER_API_URL,
});

authAxios.interceptors.request.use(
  (config) => {
    try {
      const accessToken = localStorage.getItem("AccessToken");

      if (accessToken) {
        // Authorization 헤더에 AccessToken 추가
        config.headers["AccessToken"] = accessToken;
      }

      return config;
    } catch (error) {
      return config;
    }
  },
  (error) => {
    return Promise.reject(error);
  }
);

authAxios.interceptors.response.use(
  (response) => response, // 성공적인 응답 처리

  async (error) => {
    if (error.response.status === 401) {
      // 401: AC 토큰 만료
      const refreshToken = localStorage.getItem("RefreshToken"); // 리프레시 토큰 가져오기
      const originalRequest = error.config; // 원래 요청

      try {
        // 리프레시 토큰으로 새로운 AC 토큰 요청
        const { data } = await authAxios.post("/validate/token", {
          RefreshToken: refreshToken,
        });

        // 새로운 AC 토큰 저장
        localStorage.setItem("AccessToken", data.accessToken);

        // 원래 요청 다시 시도
        originalRequest.headers["AccessToken"] = `Bearer ${data.accessToken}`;
        return api(originalRequest); // 새로운 토큰으로 요청 재실행
      } catch (err) {
        return Promise.reject(error);
      }
    }
    return Promise.reject(error);
  }
);

export default authAxios;
