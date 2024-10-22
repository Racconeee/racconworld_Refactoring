<template>
  <div class="container">
    <QuizReadyDetail></QuizReadyDetail>
    <TestMainViewApiError v-if="testStore.getTestError"></TestMainViewApiError>

    <InfiniteScroll
      :hasNext="testStore.testListhasNext"
      :onLoad="onLoad"
      :getTestError="testStore.getTestError"
    >
      <TestList :items="testStore.testList"></TestList>
    </InfiniteScroll>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useTestStore } from "@/stores/useTestStore";
import TestList from "src/components/Test/TestList.vue";
import InfiniteScroll from "@/components/Test/InfiniteScroll.vue";
import TestMainViewApiError from "@/components/Test/TestMainViewApiError.vue";
import QuizReadyDetail from "./QuizReadyDetail.vue";
import { useRoute } from "vue-router";
import { onMounted } from "vue";

const route = useRoute();
const testStore = useTestStore();

const pageNumber = ref(0);

// 데이터 로드 함수 정의
const onLoad = async (page) => {
  console.log("데이터 로드 중:", page);
  await testStore.getTestList({ pageNumber: page });
};

// const onLoadRef = (index, done) => {
//   console.log("onLoadRef : 실행");
//   console.log(pageNumber.value);
//   console.log(testStore.testListhasNext);

//   if (testStore.getTestError) {
//     console.log("서버와의 연결이 안됨");

//     return;
//   }
//   // if (!testStore.testListhasNext) {
//   //   console.log("더이상 데이터가 없음");
//   //   return;
//   // }

//   testStore.getTestList({ pageNumber: pageNumber.value }).then(() => {
//     done();
//   });

//   pageNumber.value++;
// };

onMounted(async () => {
  testStore.setCurrentTestId(route.params.testId);

  // console.log("현재 URL에서 가져온 testId:" + testStore.setCurrentTestId);
  console.log(testStore.currentTestId);

  return await testStore.getQuizList(testStore.currentTestId);
});
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
}
</style>
