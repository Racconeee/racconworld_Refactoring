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
import { useRoute } from "vue-router";
import { onMounted } from "vue";
import TestList from "src/components/Test/TestList.vue";
import InfiniteScroll from "@/components/Test/InfiniteScroll.vue";
import TestMainViewApiError from "@/components/Test/TestMainViewApiError.vue";
import QuizReadyDetail from "./QuizReadyDetail.vue";
import ShareLink from "@/components/etc/ShareLink.vue";

const route = useRoute();
const testStore = useTestStore();

const pageNumber = ref(0);

// 데이터 로드 함수 정의
const onLoad = async (page) => {
  await testStore.getTestList({ pageNumber: page });
};


onMounted(async () => {
  testStore.setCurrentTestId(route.params.testId);
  testStore.setShareLink(route.fullPath);
  await testStore.getQuizList(testStore.currentTestId);
  useHead({
    title: `Quiz Ready - Test ${testStore.currentTestId}`,
    meta: [
      {
        name: "description",
        content: `${testStore.quizList.testName}`,
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
        content: `https://racconworld.com/images/share_image.png`, 
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
