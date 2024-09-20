import axios from "axios";
// import { httpStatusCode } from "./http-status";

// 아래와 같이 VITE_SERVER_API_URL 까지 기입 해줘야한다. + vite에서는 meta를 사용해야 구조할당이 된다.
// .env 파일에서 환경 변수를 설정할떄 모든 변수는 VITE_로 시작해야 Vite가 이 변수를 빌드 시점에 인식해서 사용한다.
// .env 뒤에 폴더 명이 있어도 그냥 import.meta.env를 통해서 접근이 가능하다.

const VITE_SERVER_API_URL = import.meta.env.VITE_SERVER_API_URL;

// function apiAxios() {
//   console.log("VITE_SERVER_API_URL : ", VITE_SERVER_API_URL);

//   const instance = axios.create({
//     baseURL: VITE_SERVER_API_URL,
//     headers: {
//       "Content-Type": "application/json;charset=utf-8",
//     },
//   });
//   return instance;
// }

// function authApiAxios() {
//   const instance = axios.create({
//     baseURL: VITE_SERVER_API_URL,
//     headers: {
//       "Content-Type": "application/json;charset=utf-8",
//     },
//   });
//   return instance;
// }

// export { apiAxios, authApiAxios };

const authAxios = axios
  .create
  //   baseURL: VITE_SERVER_API_URL,
  // });
  // authAxios.interceptors.request.use(
  //   (config) => {
  //     const accessToken = localStorage.getItem("AccessToken");
  //     console.log("accessToken 값은", accessToken);
  //     console.log("accessToken 값은", accessToken);
  //     if (accessToken) {
  //       // Authorization 헤더에 Bearer 토큰 추가
  //       config.headers["AccessToken"] = `${accessToken}`;
  //     }
  //     console.log("config의 값은", config);

  //     return config;
  //   },
  //   (error) => {
  //     return Promise.reject(error);
  //   }
  ();

// 응답 인터셉터 설정
authAxios.interceptors.response.use(
  (response) => response, // 성공적인 응답 처리

  async (error) => {
    if (error.response.status === 401) {
      // 401: AC 토큰 만료
      const refreshToken = localStorage.getItem("refreshToken"); // 리프레시 토큰 가져오기
      const originalRequest = error.config; // 원래 요청

      try {
        // 리프레시 토큰으로 새로운 AC 토큰 요청
        const { data } = await authAxios.post("/validate/token", {
          token: refreshToken,
        });

        // 새로운 AC 토큰 저장
        localStorage.setItem("accessToken", data.accessToken);

        // 원래 요청 다시 시도
        originalRequest.headers["Authorization"] = `Bearer ${data.accessToken}`;
        return api(originalRequest); // 새로운 토큰으로 요청 재실행
      } catch (err) {
        console.log("리프레시 토큰도 만료되었습니다.");
        // 로그아웃 처리나 로그인 페이지로 이동
        return Promise.reject(error);
      }
    }
    return Promise.reject(error);
  }
);

export default authAxios;
