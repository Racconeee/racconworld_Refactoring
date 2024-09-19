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
import { ref } from "vue";
import { useAuthStore } from "src/stores/useAuthStore";
import { useRouter } from "vue-router";

const username = ref("");
const password = ref("");
const authStore = useAuthStore();
const router = useRouter();

const submitLoginInfo = async () => {
  console.log(username, password);
  console.log(username, password);
  await authStore.adminLogin(username.value, password.value);
  console.log(authStore.isLoginValue);

  if (authStore.isLoginValue) {
    console.log("admin page로 이동");

    router.push({ name: "admin" });
  } else {
    alert("로그인 싫패");
  }
};
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
