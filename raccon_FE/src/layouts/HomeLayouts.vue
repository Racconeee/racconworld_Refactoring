<template>
  <q-layout view="hHh lpR fff" class="bg-grey-2">
    <q-header
      bordered
      class="bg-white text-grey-9"
      style="max-width: 800px; margin: 0 auto"
    >
      <q-toolbar class="flex">
        <q-btn flat dense to="/home">
          <q-avatar rounded class="q-ma-sm q-mr-md">
            <img src="/images/logo.png" />
          </q-avatar>
          <q-toolbar-title class="text-weight-bolder h1">
            Racconworld
          </q-toolbar-title>
        </q-btn>
        <q-space />
        <div class="grid" style="text-align: center">
          <div>총 방문자수</div>
          <div>{{ testStore.totalVisited }}</div>
        </div>
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

    <q-page-container class="bg-light-blue-2">
      <router-view />
    </q-page-container>

    <q-footer
      elevated
      class="bg-teal-6 text-gray items-center justify-center q-pa-xs"
    >
      <div class="q-mt-xs text-center">
        © 2024 i.racconworld.i@gmail.com. All rights reserved.
      </div>
      <div class="q-mt-xs text-center">
        문의하기 : i.racconworld.i@gmail.com
      </div>
      <div class="q-mt-xs row justify-around">
        <q-btn flat label="개인정보취급방침" to="/about/privacy-policy" />
        <q-btn flat label="서비스 이용약관" to="/about/terms-of-service" />
      </div>
    </q-footer>
  </q-layout>
</template>

<script setup>
import { useTestStore } from "src/stores/useTestStore";
import { onMounted } from "vue";

const testStore = useTestStore();

onMounted(() => {
  testStore.getTotalVisited();
});
</script>
