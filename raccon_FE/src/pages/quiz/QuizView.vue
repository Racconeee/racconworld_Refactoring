<template>
  <div class="bg-teal-2" :style="{ height: '100vh' }">
    <div>
      <QuizChoice></QuizChoice>
    </div>
  </div>
</template>

<script setup>
import QuizChoice from "@/components/quiz/QuizChoice.vue";
import { onMounted } from "vue";
import { useTestStore } from "@/stores/useTestStore";
import { useRouter } from "vue-router";

const router = useRouter();
const testStore = useTestStore();

onMounted(async () => {
  if (!testStore.currentTestId) {
    router.push({ path: "/" }); // 홈 경로로 리다이렉트
    return;
  }
  
  if (testStore.currentTestId) {
    return await testStore.getQuizList(testStore.currentTestId).then(() => {});
  }
  return await testStore
    .getQuizList(localStorage.getItem("testId"))
    .then(() => {});

});

</script>

<style></style>
