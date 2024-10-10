<template>
  <div class="container bg-cyan-6">
    <div class="text-h5 text-center text-weight-bold q-mb-xl">!! LOGIN !!</div>

    <q-form class="q-gutter-y-md text-center" :style="{ width: '30vw' }">
      <q-input
        bg-color="cyan-1"
        placeholder="ID"
        outlined
        dense
        rounded
        v-model="username"
      ></q-input>
      <q-input
        bg-color="cyan-1"
        placeholder="Password"
        outlined
        dense
        rounded
        v-model="password"
      ></q-input>
      <q-separator color="white" />
      <q-btn
        label="로그인"
        flat
        unelevated
        rounded
        class="bg-cyan-1"
        :style="{ width: '30vw' }"
        @click="submitLoginInfo"
      ></q-btn>
    </q-form>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useAuthStore } from "src/stores/useAuthStore";
import { useRouter } from "vue-router";

const username = ref("");
const password = ref("");
const authStore = useAuthStore();
const router = useRouter();

//중요한점 동기로 처리하는게 중요하다.
//로그인의 결과가 나오지도 않았는데 넘어가버리면 안되기에

// 로그인 로직은 비교적 간단하다.
// id . pw 로 로그인 하고 store에서 로그인이 되었다고 하면
// 로그인함
const submitLoginInfo = async () => {
  console.log(username, password);
  await authStore.adminLogin(username.value, password.value);

  console.log("authStore.isLoginValue : ->" + authStore.isLoginValue);
  console.log(
    " submitLoginInfo : authStore.acctoken : ->" + authStore.accessToken
  );

  if (authStore.isLoginValue) {
    alert("로그인 성공! 환영합니다.");
    router.push({ name: "adminhome" });
  } else {
    alert("로그인 실패");
  }
};

//화면이 onMounted가 되었을떄
//로그인한 전적이 있는 사용자는 재 로그인을 안하게 함
// 로직
// 1. authStore.isLoginValue 에서 로그인을 했는지 확인하기
// 2. 토큰 값이 있다면 가져와서 서버에서 인증 받아보기
//  2-1 값이 있다면 authStore.isLoginValue 값 변경해주고 관리자 페이지 이동
//  2-2 값이 없다면 로그인 정보가 없다라는 판단으로 재 로그인
onMounted(async () => {
  console.log("LoginView 에서 찍힌 로그 ");
  console.log(authStore.isLoginValue);

  console.log(authStore.accessToken);
  console.log(localStorage.getItem("AccessToken"));

  if (authStore.isLoginValue) {
    alert("이미 로그인을 하셨기 때문에 다시 안하셔도 됩니다");
    router.push({ name: "adminhome" });
  } else if (authStore.accessToken) {
    await authStore.validateAccessToken(authStore.accessToken);
    console.log("현재 존재하는 토큰 값 : " + authStore.accessToken);

    if (authStore.isLoginValue) {
      alert("이미 로그인을 하셨기 때문에 다시 안하셔도 됩니다");
      router.push({ name: "adminhome" });
    }
  } else {
    console.log("로그인 정보가 없습니다.");
  }
});
</script>

<style lang="scss" scoped>
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
</style>
