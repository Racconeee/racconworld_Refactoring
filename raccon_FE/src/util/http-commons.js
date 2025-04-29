import axios from "axios";

const VITE_SERVER_API_URL = import.meta.env.VITE_SERVER_API_URL;

const authAxios = axios.create({
  baseURL: VITE_SERVER_API_URL,
});

authAxios.interceptors.request.use(
  (config) => {
    try {
      const accessToken = localStorage.getItem("AccessToken");

      if (accessToken) {
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
      const refreshToken = localStorage.getItem("RefreshToken"); // 리프레시 토큰 가져오기
      const originalRequest = error.config; // 원래 요청

      try {
        // 리프레시 토큰으로 새로운 AC 토큰 요청
        const { data } = await authAxios.post("/validate/token", {
          RefreshToken: refreshToken,
        });

        localStorage.setItem("AccessToken", data.accessToken);

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
