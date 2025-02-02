import axios from "axios";
import { defineStore } from "pinia";
import { ref } from "vue";

const VITE_SERVER_API_URL = import.meta.env.VITE_SERVER_API_URL;

export const useAuthStore = defineStore("auth", () => {
  const isLoginValue = ref(false);
  const accessToken = ref(localStorage.getItem("AccessToken") || null);
  const refreshToken = ref(localStorage.getItem("RefreshToken") || null);

  const adminLogin = async function (id, pw) {
    const loginData = {
      username: id,
      password: pw,
    };

    await axios({
      method: "post",
      url: `${VITE_SERVER_API_URL}/login`,
      headers: {
        "Content-Type": "application/json",
      },
      data: loginData,
    })
      .then((res) => {
        if (res.headers["accesstoken"]) {
          isLoginValue.value = true;
          localStorage.setItem("AccessToken", res.headers["accesstoken"]);
          localStorage.setItem("RefreshToken", res.headers["refreshtoken"]);
          accessToken.value = res.headers["accesstoken"];
          refreshToken.value = res.headers["refreshtoken"];
        } else {
          isLoginValue.value = false;
        }
      })
      .catch((err) => {});
  };

  const getAccessToken = () => {
    if (accessToken.value) {
      return accessToken.value;
    } else {
      const accessToken_value = localStorage.getItem("AccessToken");
      if (accessToken_value) {
        accessToken.value = accessToken_value;
        return accessToken;
      } else {
        return null;
      }
    }
  };

  //토큰 값이 있다면 토큰 값을 localStorage 에 저장하고 없다면 없애기
  const setAccessToken = (token) => {
    if (token) {
      localStorage.setItem("AccessToken", token);
    } else {
      localStorage.removeItem("AccessToken");
    }
    accessToken.value = token;
  };

  const getRefreshToken = () => {
    if (refreshToken.value) {
      return refreshToken.value;
    } else {
      const refreshToken_value = localStorage.getItem("RefreshToken");
      if (refreshToken_value) {
        refreshToken.value = refreshToken_value;
        return refreshToken;
      } else {
        return null;
      }
    }
  };

  const setRefreshToken = (token) => {
    if (token) {
      localStorage.setItem("refreshToken", token);
    } else {
      return false;
    }
  };
  //토큰 정리하기 내일
  //이부분 해야됨
  //AC토큰 인증하기
  //토큰을 인증하는데 만약 401 토큰이 만료되었다고 하게 되면 리프레쉬 토큰을 통해서 재발급 받기
  const validateAccessToken = async (accessToken_value) => {
    "검증하는 validateAccessToken-accessToken의 값은 : ", accessToken_value;
    const AccessTokenDto = {
      accessToken: accessToken_value,
    };

    await axios({
      method: "post",
      url: `${VITE_SERVER_API_URL}/validate/token`,
      data: AccessTokenDto,
    })
      .then((res) => {
        if (res.status === 200) {
          isLoginValue.value = true;
        } else {
          isLoginValue.value = false;
        }
      })
      .catch(async (err) => {
        if (err.response.status === 401) {
          if (refreshToken.value) {
            await validateRefreshToken(refreshToken.value);
            return;
          }
        }
        isLoginValue.value = false;
      });
  };

  const validateRefreshToken = async (refreshToken_value) => {
    await axios({
      method: "post",
      url: `${VITE_SERVER_API_URL}/validate/refreshtoken`,
      headers: {
        RefreshToken: refreshToken_value, // Authorization 헤더 추가
      },
    })
      .then((res) => {
        isLoginValue.value = true;
        if (res.headers["accesstoken"]) {
          isLoginValue.value = true;
          localStorage.setItem("AccessToken", res.headers["accesstoken"]);
          localStorage.setItem("RefreshToken", res.headers["refreshtoken"]);
          accessToken.value = res.headers["accesstoken"];
          refreshToken.value = res.headers["refreshtoken"];
        } else {
          isLoginValue.value = false;
        }
      })
      .catch((err) => {
        isLoginValue.value = false;
      });
  };

  return {
    isLoginValue,
    adminLogin,
    accessToken,
    refreshToken,
    getAccessToken,
    setAccessToken,
    getRefreshToken,
    setRefreshToken,
    validateAccessToken,
  };
});
