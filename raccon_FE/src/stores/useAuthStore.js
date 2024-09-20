import axios from "axios";
import { defineStore } from "pinia";
import { ref } from "vue";
import { useRouter } from "vue-router";

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
        console.log("res 입니다");
        console.log(res.headers["accesstoken"]);
        console.log(res.headers["refreshtoken"]);
        if (res.headers["accesstoken"]) {
          isLoginValue.value = true;
          localStorage.setItem("AccessToken", res.headers["accesstoken"]);
          localStorage.setItem("RefreshToken", res.headers["refreshtoken"]);
          console.log("로그인 성공");
        } else {
          isLoginValue.value = false;
        }
      })
      .catch((err) => {
        console.log(err);
      });
  };

  // api 에서 사용하게 설정 해놓음 pinia에 토큰이 있다면 반환 하고 없다면 설정하고
  // 없다면 localStorage에서 값 가져오기 그래도 없다면 로그인 시키게 하기
  // 이런식으로 명시적으로 토큰의 값을 초기화 ? 최신화 시킬 수도 있지만.
  // 새로고침 하게 되도 accessToken = ref(localStorage.getItem("AccessToken") || null);
  // 이 부분에서 값을 가져오기에 굳이 getAccessToken 함수를 만들 필요가 없다.
  const getAccessToken = () => {
    if (accessToken.value) {
      return accessToken.value;
    } else {
      // pinia 에서 새로고침해서 토큰 값이 없는 경우에는 로컬 스토리지에서 토큰을 가지고옴
      //있다면 피니아에 다시 설정해주고 아니라면
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

  const validateAccessToken = async (accessToken_value) => {
    console.log("검증하는 accessToken의 값은 : ", accessToken_value);
    const AccessTokenDto = {
      accessToken: accessToken_value,
    };

    console.log("AccessTokenDto", accessToken_value);

    await axios({
      method: "post",
      url: `${VITE_SERVER_API_URL}/validate/token`,
      data: AccessTokenDto,
    })
      .then((res) => {
        if (res.status === 200) {
          isLoginValue.value = true;
        } else {
          isLoginValue.value = fas;
        }

        console.log(res);
      })
      .catch((err) => {
        console.log(err);
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
