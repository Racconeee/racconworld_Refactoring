<template>
  <q-layout view="hHh lpR fff" class="bg-grey-2">
    <q-header bordered class="bg-white text-grey-9">
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

      <q-separator color="orange" inset />
      <div class="row items-centers">
        <q-btn
          class="text-weight-bolder col-6"
          stretch
          flat
          label="home"
          to="/home"
        />
        <q-btn
          class="text-weight-bolder col-6"
          stretch
          flat
          label="About"
          to="/about"
        />
      </div>
    </q-header>

    <q-page-container :style="pageContainerStyles" class="bg-teal-2">
      <router-view />
    </q-page-container>

    <q-footer elevated class="bg-teal-6">
      <q-toolbar>
        <q-toolbar-title>Copyright@racconworld.com</q-toolbar-title>
      </q-toolbar>
    </q-footer>
  </q-layout>
</template>

<script setup>
import { computed } from "vue";
import { useRoute } from "vue-router";

const route = useRoute();
const pageContainerStyles = computed(() => ({
  maxWidth: route.meta?.width || "1080px",
  margin: "0 auto",
}));
</script>
