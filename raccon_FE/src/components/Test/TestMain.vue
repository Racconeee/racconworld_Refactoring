<template>
  <div class="container">
    <TestMainViewApiError v-if="testStore.getTestError"></TestMainViewApiError>

    <InfiniteScroll
      :hasNext="testStore.testListhasNext"
      :onLoad="onLoad"
      :getTestError="testStore.getTestError"
    >
    </InfiniteScroll>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useTestStore } from "@/stores/useTestStore";
import InfiniteScroll from "@/components/Test/InfiniteScroll.vue";
import TestMainViewApiError from "./TestMainViewApiError.vue";

const testStore = useTestStore();
const pageNumber = ref(0);

// 데이터 로드 함수 정의
const onLoad = async (page) => {
  console.log("데이터 로드 중:", page);
  await testStore.getTestList({ pageNumber: page });
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
}
</style>
