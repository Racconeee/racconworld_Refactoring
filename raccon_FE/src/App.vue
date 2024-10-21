<template>
  <div style="max-width: 800px; min-width: 340px; margin: 0 auto">
    <router-view />
  </div>
</template>

<script setup>
defineOptions({
  name: "App",
});
</script>

<style lang="scss" scoped>
@import "./assets/styles.css"; /* 전역 스타일 파일 import */
</style>
