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

const onLoad = async (page) => {
  await testStore.getTestList({ pageNumber: page });
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
}
</style>

