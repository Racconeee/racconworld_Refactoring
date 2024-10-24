<template>
  <div class="container">
    <QuizReadyDetail></QuizReadyDetail>
    <ShareLink></ShareLink>

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
import { useHead } from "@vueuse/head";
import { useTestStore } from "@/stores/useTestStore";
import TestList from "src/components/Test/TestList.vue";
import InfiniteScroll from "@/components/Test/InfiniteScroll.vue";
import TestMainViewApiError from "@/components/Test/TestMainViewApiError.vue";
import QuizReadyDetail from "./QuizReadyDetail.vue";
import ShareLink from "@/components/ShareLink.vue";
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

onMounted(async () => {
  testStore.setCurrentTestId(route.params.testId);

  testStore.setShareLink(route.fullPath);

  // console.log("현재 URL에서 가져온 testId:" + testStore.setCurrentTestId);
  console.log(testStore.currentTestId);
  // await testStore.getTestIdToView(testStore.currentTestId);
  await testStore.getQuizList(testStore.currentTestId);

  useHead({
    title: `Quiz Ready - Test ${testStore.currentTestId}`,
    meta: [
      {
        name: "description",
        content: `This is the Quiz Ready page for Test ${testStore.quizList.testName}. Get ready!`,
      },
      {
        property: "og:title",
        content: `${testStore.quizList.testName}`,
      },
      {
        property: "og:description",
        content: `재미있게 즐겨보세요`,
      },
      {
        property: "og:image",
        content: `${testStore.getVITE_NGINX_IMG_URL()}/file/${
          testStore.currentTestId
        }/main`, // 고정된 이미지 또는 동적 이미지 경로 설정
      },
      {
        property: "og:url",
        content: window.location.href,
      },
    ],
  });
});
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
</style>
