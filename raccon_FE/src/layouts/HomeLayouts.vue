<template>
  <q-layout view="hHh lpR fff" class="bg-grey-2">
    <q-header
      bordered
      class="bg-white text-grey-9"
      style="max-width: 800px; margin: 0 auto"
    >
      <q-toolbar>
        <q-btn flat dense to="/home">
          <q-avatar rounded class="q-ma-sm q-mr-md">
            <img src="/images/logo.png" />
          </q-avatar>
          <q-toolbar-title class="text-weight-bolder h1">
            Racconworld
          </q-toolbar-title>
        </q-btn>
      </q-toolbar>
      <div class="row items-center">
        <q-btn
          class="text-weight-bolder col-6"
          stretch
          flat
          label="home"
          to="/home"
        />
        <!-- <q-separator color="orange" inset vertical /> -->
        <q-btn
          class="text-weight-bolder col-6"
          stretch
          flat
          label="About"
          to="/about"
        />
      </div>
    </q-header>

    <q-page-container class="bg-teal-2">
      <router-view />
    </q-page-container>

    <q-footer
      elevated
      class="bg-teal-6 text-gray row items-center justify-center"
    >
      <div class="q-mt-sm">
        <q-btn
          flat
          label="개인정보취급방침"
          to="/privacy-policy"
          class="q-ml-md"
        />
        <q-btn
          flat
          label="서비스 이용약관"
          to="/terms-of-service"
          class="q-ml-md"
        />
      </div>
      <div>© 2024 Raccoonworld. All rights reserved.</div>
      <div class="q-mt-sm">문의하기 : racconworld@gmail.com</div>
    </q-footer>
  </q-layout>
</template>

<script setup>
// import { computed } from "vue";
// import { useRoute } from "vue-router";

// const route = useRoute();
// const pageContainerStyles = computed(() => ({
//   maxWidth: route.meta?.width || "1080px",
//   margin: "0 auto",
// }));
</script>
