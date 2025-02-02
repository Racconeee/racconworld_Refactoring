<template>
  <div style="max-width: 800px; min-width: 340px; margin: 0 auto">
    <router-view />
  </div>
</template>

<script setup>
import { useHead } from "@vueuse/head";

defineOptions({
  name: "App",
});

useHead({
  title: "Raccon World",
  meta: [
    {
      name: "description",
      content:
        "Welcome to Raccon World! Your go-to platform for fun quizzes and more.",
    },
    {
      property: "og:title",
      content: "Raccon World",
    },
    {
      property: "og:description",
      content: "Enjoy various fun quizzes at Raccon World!",
    },
    {
      property: "og:image",
      content: "/images/logo.png",
    },
  ],
});
</script>

<style lang="scss" scoped>
@import "./assets/styles.css"; /* 전역 스타일 파일 import */
</style>
