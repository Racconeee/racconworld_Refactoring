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

const testStore = useTestStore();

onMounted(async () => {
  console.log("testStore.currentTestId", testStore.currentTestId);
  if (testStore.currentTestId) {
    return await testStore.getQuizList(testStore.currentTestId).then(() => {});
  }
  return await testStore
    .getQuizList(localStorage.getItem("testId"))
    .then(() => {});

  // quizList 비동기 데이터 호출
});
</script>

<style></style>
