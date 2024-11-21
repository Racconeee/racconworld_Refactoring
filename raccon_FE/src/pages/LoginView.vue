<template>
  <div class="container bg-cyan-6">
    <div class="text-h5 text-center text-weight-bold q-mb-xl">!! LOGIN !!</div>

    <q-form class="q-gutter-y-md text-center" :style="{ width: '45vw' }">
      <q-input
        bg-color="cyan-1"
        placeholder="ID"
        outlined
        dense
        rounded
        v-model="username"
      ></q-input>
      <!-- <q-input
        bg-color="cyan-1"
        placeholder="Password"
        outlined
        dense
        rounded
        v-model="password"
      ></q-input> -->
      <q-input
        v-model="password"
        bg-color="cyan-1"
        placeholder="Password"
        outlined
        dense
        rounded
        :type="isPwd ? 'password' : 'text'"
      >
        <template v-slot:append>
          <q-icon
            :name="isPwd ? 'visibility_off' : 'visibility'"
            class="cursor-pointer"
            @click="isPwd = !isPwd"
          />
        </template>
      </q-input>
      <!-- <q-separator color="white" /> -->
      <q-btn
        label="로그인"
        flat
        unelevated
        rounded
        class="bg-cyan-1"
        :style="{ width: '45vw' }"
        @click="submitLoginInfo"
      ></q-btn>
    </q-form>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useAuthStore } from "src/stores/useAuthStore";
import { useRouter } from "vue-router";

const isPwd = ref(true);
const username = ref("");
const password = ref("");
const authStore = useAuthStore();
const router = useRouter();

const submitLoginInfo = async () => {
  await authStore.adminLogin(username.value, password.value);

  if (authStore.isLoginValue) {
    alert("로그인 성공! 환영합니다.");
    router.push({ name: "adminhome" });
  } else {
    alert("로그인 실패");
  }
};

onMounted(async () => {
  if (authStore.isLoginValue) {
    alert("이미 로그인을 하셨기 때문에 다시 안하셔도 됩니다");
    router.push({ name: "adminhome" });
  } else if (authStore.accessToken) {
    await authStore.validateAccessToken(authStore.accessToken);

    if (authStore.isLoginValue) {
      alert("이미 로그인을 하셨기 때문에 다시 안하셔도 됩니다");
      router.push({ name: "adminhome" });
    }
  } else {
    alert("로그인 정보가 존재하지 않습니다.");
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
